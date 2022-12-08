package com.example.planti.ui.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planti.Bdsqlite;
import com.example.planti.EditProfile;
import com.example.planti.Login;
import com.example.planti.MainActivity;
import com.example.planti.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    TextView tvNombre, tvEmail, tvDescripcion;
    Button btnEditar, btnCerrarSesion;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tvNombre = view.findViewById(R.id.tvNombre);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        btnEditar = view.findViewById(R.id.btnEditar);
        btnCerrarSesion = view.findViewById(R.id.btnGuardar);

        btnEditar.setOnClickListener(this);
        btnCerrarSesion.setOnClickListener(this);

        cargarDatos();
    }

    @SuppressLint({"Range", "SetTextI18n"})
    private void cargarDatos() {
        Bdsqlite admin = new Bdsqlite(getActivity(), "planti", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Bundle b = getActivity().getIntent().getExtras();
        String email = b.getString("logged_user");
        System.out.println(email);
        String query = "select name, description from users where email='" + email +"'";
        Cursor fila = bd.rawQuery(query, null);
        if (fila.moveToFirst()) {
            String name = fila.getString(fila.getColumnIndex("name"));
            String description = fila.getString(fila.getColumnIndex("description"));
            System.out.println(name);
            System.out.println(description);
            tvNombre.setText(name);
            tvEmail.setText(email);
            tvDescripcion.setText(description);
        } else {
            Toast.makeText(getActivity(), "Ha ocurrido un error", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        if(view == btnEditar){
            Intent intent = new Intent(getActivity(), EditProfile.class);
            startActivity(intent);
        }else if(view == btnCerrarSesion){
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
        }
    }
}