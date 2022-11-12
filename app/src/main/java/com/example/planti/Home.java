package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity implements View.OnClickListener {

    Button calificar1, calificar2, btnPerfil, btnCrearPlanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        calificar1 = findViewById(R.id.calificar1);
        calificar2 = findViewById(R.id.calificar2);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnCrearPlanta = findViewById(R.id.btnCrearPlanta);

        calificar1.setOnClickListener(this);
        calificar2.setOnClickListener(this);
        btnPerfil.setOnClickListener(this);
        btnCrearPlanta.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnCrearPlanta) {
            Intent intent = new Intent(this, CreatePlant.class);
            startActivity(intent);
        }
        if(view == btnPerfil){
            Intent intent = new Intent(this, Profile.class);
            startActivity(intent);
        }
        if(view == calificar1 || view == calificar2){
            Intent intent = new Intent(this, RatePlant.class);
            startActivity(intent);
        }
    }
}