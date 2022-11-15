package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmail, edtPassword;
    Button btnIniciarSesion, btnRegisterLogin;

    Bdsqlite admin;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegisterLogin = findViewById(R.id.btnRegisterLogin);

        btnIniciarSesion.setOnClickListener(this);
        btnRegisterLogin.setOnClickListener(this);

        admin = new Bdsqlite(this, "planti", null, 1);
        bd = admin.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        if(view == btnIniciarSesion){
            String query = "select email, password from users where email='" + edtEmail.getText()+"' and password='"+edtPassword.getText()+"'";
            Cursor fila = bd.rawQuery(query, null);
            if (fila.moveToFirst()) {
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Correo o contraseña equivocada", Toast.LENGTH_LONG).show();
            }
        }
        if(view == btnRegisterLogin){
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }
}