package com.example.planti.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.planti.Bdsqlite;
import com.example.planti.CreatePlant;
import com.example.planti.R;
import com.example.planti.RatePlant;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ArrayList<HashMap<String, String>> arraylist = new ArrayList<>();
    Bdsqlite admin;
    SQLiteDatabase bd;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        admin = new Bdsqlite(getActivity(), "planti", null, 1);
        bd = admin.getWritableDatabase();
        try {
            llenarlista();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void llenarlista() throws JSONException {
        String query = "select * from plants";
        Cursor cursor = bd.rawQuery(query, null);
        JSONArray ja = null;

        if (cursor.moveToFirst()){
            while(!cursor.isAfterLast()){
                HashMap<String, String> map = new HashMap<String, String>();
                @SuppressLint("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String plantKind = cursor.getString(cursor.getColumnIndex("plantKind"));
                @SuppressLint("Range")
                String bitmap = cursor.getString(cursor.getColumnIndex("imageBitmap"));
                @SuppressLint("Range")
                String description = cursor.getString(cursor.getColumnIndex("description"));


                map.put("id", id);
                map.put("name", name);
                map.put("plantKind", plantKind);
                map.put("bitmap", bitmap);
                map.put("description", description);
                System.out.println(map);
                arraylist.add(map);

                cursor.moveToNext();
            }
        }
        cursor.close();

        //2 arreglos
        String[] origen = new String[4];
        origen[0] = "name";
        origen[1] = "plantKind";
        origen[2] = "description";
        origen[3] = "bitmap";

        int[] destino = new int[4];
        destino[0] = R.id.tvNombre;
        destino[1] = R.id.tvTipo;
        destino[2] = R.id.tvDescripcion;
        destino[3] = R.id.ivPlant;
        SimpleAdapter listadapter = new SimpleAdapter(getActivity(), arraylist, R.layout.item_home, origen, destino);
        ListView lvVisitas = getView().findViewById(R.id.lvHome);
        lvVisitas.setAdapter(listadapter);

    }
}