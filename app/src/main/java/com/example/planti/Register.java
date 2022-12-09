package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Register extends AppCompatActivity implements View.OnClickListener {
    Button btnRegister, btnIniciarSesionRegister;
    EditText edtName, edtDescription, edtEmail, edtPasswordRegister, edtPasswordRegister2;
    TextView tvErrorRegister;

    Bdsqlite admin;
    SQLiteDatabase bd;
    int id;
    String name, description, email, pass1, pass2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        edtName = findViewById(R.id.edtName);
        edtDescription = findViewById(R.id.edtDescription);
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
        name = String.valueOf(edtName.getText());
        description = String.valueOf(edtDescription.getText());
        email = String.valueOf(edtEmail.getText());
        pass1 = String.valueOf(edtPasswordRegister.getText());
        pass2 = String.valueOf(edtPasswordRegister2.getText());
    }

    private int countUsers() {
        Cursor fila = bd.rawQuery("select count(*) from users", null);
        if (fila.moveToFirst()) {
            String result = fila.getString(0);
            return Integer.parseInt(result) + 1;
        } else {
            return -1;
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnIniciarSesionRegister) {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
        if (view == btnRegister) {
            getData();
            if(id == -1){
                Toast.makeText(this, "Ocurrió un error al intentar crear el usuario", Toast.LENGTH_LONG).show();
            }else if (!pass1.equals(pass2)){
                tvErrorRegister.setText("La contraseña no coincide");
            }else {
                tvErrorRegister.setText("");

                ContentValues registro = new ContentValues();
                registro.put("id", id);
                registro.put("name", name);
                registro.put("description", description);
                registro.put("email", email);
                registro.put("password", pass1);

                bd.insert("users", null, registro);
                Toast.makeText(this, "Se creó el usuario satisfactoriamente", Toast.LENGTH_LONG).show();
                bd.close();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("logged_user",email);
                startActivity(intent);
            }
        }
    }
}