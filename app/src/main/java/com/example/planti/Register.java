package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister;
    Button btnIniciarSesionRegister;
    EditText edtEmail, edtPasswordRegister, edtPasswordRegister2;
    TextView tvErrorRegister;

    Bdsqlite admin;
    SQLiteDatabase bd;
    int id;
    String email;
    String pass1;
    String pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        edtEmail = findViewById(R.id.edtEmail);
        edtPasswordRegister2 = findViewById(R.id.edtPasswordRegister2);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        tvErrorRegister = findViewById(R.id.tvErrorRegister);

        btnRegister.setOnClickListener(this);
        btnIniciarSesionRegister = findViewById(R.id.btnIniciarSesionRegister);
        btnIniciarSesionRegister.setOnClickListener(this);

        admin = new Bdsqlite(this, "planti", null, 1);
        bd = admin.getWritableDatabase();
        getData();
    }

    private void getData() {
        id = countUsers();
        email = String.valueOf(edtEmail.getText());
        pass1 = String.valueOf(edtPasswordRegister.getText());
        pass2 = String.valueOf(edtPasswordRegister2.getText());
    }

    private int countUsers() {
        return 1; // toca conectar la bd para contar que ids estan libres
    }

    @Override
    public void onClick(View view) {
        if (view == btnIniciarSesionRegister) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        if (view == btnRegister) {
            getData();
            if (pass1 != pass2){
                tvErrorRegister.setText("Contraseña no coincide");
            }else {
                tvErrorRegister.setText("");


                ContentValues registro = new ContentValues();
                registro.put("id", id);
                registro.put("email", email);
                registro.put("password", pass1);

                bd.insert("paciente", null, registro);
//        bd.close();
                Toast.makeText(this, "Se inserto satisfactoriamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            }
        }
    }
}