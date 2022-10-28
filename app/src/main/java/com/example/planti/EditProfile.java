package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    TextView tvNombre, tvEmail, tvDescripcion;
    Button btnEditarFoto, btnCancelar, btnCerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        btnEditarFoto = findViewById(R.id.btnEditarFoto);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnEditarFoto.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        btnCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnEditarFoto){
            Intent intent = new Intent(this, LoadPicture.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
    }
}