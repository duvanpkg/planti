package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button btnIniciarSesion, btnRegisterLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(this);

        btnRegisterLogin = findViewById(R.id.btnRegisterLogin);
        btnRegisterLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnIniciarSesion){
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        if(view == btnRegisterLogin){
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }
}