package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    Button btnIniciarSesionRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnIniciarSesionRegister = findViewById(R.id.btnIniciarSesionRegister);
        btnIniciarSesionRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnIniciarSesionRegister){
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        if(view == btnRegister){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
    }
}