package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    TextView tvNombre, tvEmail, tvDescripcion;
    Button btnEditar, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        btnEditar = findViewById(R.id.btnEditar);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnEditar.setOnClickListener(this);
        btnCerrarSesion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnEditar){
            Intent intent = new Intent(this, EditProfile.class);
            startActivity(intent);
        }else if(view == btnCerrarSesion){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }
}