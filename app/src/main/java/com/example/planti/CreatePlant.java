package com.example.planti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planti.ui.main.HomeFragment;


public class CreatePlant extends AppCompatActivity implements View.OnClickListener {

    Button btnCrear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plant);
        btnCrear = findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnCrear) {
            // todo: guardar info de la planta en la base de datos
            finish();
        }
    }
}


