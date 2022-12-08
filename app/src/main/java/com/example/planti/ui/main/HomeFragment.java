package com.example.planti.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
public class HomeFragment extends Fragment implements View.OnClickListener {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

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

    // Relevant code starts here!!!

    Bdsqlite admin;
    SQLiteDatabase bd;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        admin = new Bdsqlite(getActivity(), "planti", null, 1);
        bd = admin.getWritableDatabase();
        String query = "select * from plants";
        Cursor cursor = bd.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range")
                String plantKind = cursor.getString(cursor.getColumnIndex("plantKind"));
                @SuppressLint("Range")
                byte[] image = cursor.getBlob(cursor.getColumnIndex("imageBitmap"));
                @SuppressLint("Range")
                String description = cursor.getString(cursor.getColumnIndex("description"));


                TextView tvNombre = new TextView(getActivity());
                tvNombre.setText(name);
                tvNombre.setTextSize(30);
                tvNombre.setTextColor(Color.parseColor("#000000"));
                tvNombre.setGravity(Gravity.CENTER_HORIZONTAL);

                ImageView iv = new ImageView(getActivity());
                try{
                    iv.setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image.length));
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, 500);
                    layoutParams.gravity=Gravity.CENTER;
                    iv.setLayoutParams(layoutParams);

                }catch (Exception e){
                    //show nothing
                }

                TextView tvTipo = new TextView(getActivity());
                tvTipo.setText(plantKind);
                tvTipo.setTextSize(20);
                tvTipo.setTextColor(Color.parseColor("#000000"));
                tvTipo.setGravity(Gravity.CENTER_HORIZONTAL);

                TextView tvDescripcion = new TextView(getActivity());
                tvDescripcion.setText(description);
                tvDescripcion.setTextSize(15);
                tvDescripcion.setTextColor(Color.parseColor("#000000"));
                tvDescripcion.setGravity(Gravity.CENTER_HORIZONTAL);

                Button btn = new Button(getActivity());
                btn.setText("Calificar");
                btn.setOnClickListener(this);

                LinearLayout layout = getActivity().findViewById(R.id.layout);
                layout.addView(tvNombre);
                layout.addView(tvTipo);
                layout.addView(tvDescripcion);
                layout.addView(iv);
                layout.addView(btn);

                cursor.moveToNext();
            }
        }

        cursor.close();

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(),RatePlant.class);
        startActivity(intent);
    }
}