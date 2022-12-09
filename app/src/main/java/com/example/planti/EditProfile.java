package com.example.planti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.planti.ui.main.ProfileFragment;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {
    TextView tvNombre, tvEmail, tvDescripcion;
    Button btnCancelar, btnGuardar;
    String oldEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        tvDescripcion = findViewById(R.id.tvDescripcion);

        Bundle b = this.getIntent().getExtras();
        String oldName = b.getString("name");
        oldEmail = b.getString("email");
        String oldDescription = b.getString("description");

        tvNombre.setText(oldName);
        tvEmail.setText(oldEmail);
        tvDescripcion.setText(oldDescription);

        btnCancelar = findViewById(R.id.btnCancelar);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnCancelar.setOnClickListener(this);
        btnGuardar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCancelar){
            finish();
        }else if(view == btnGuardar){
            Bdsqlite admin = new Bdsqlite(this, "planti", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String name = String.valueOf(tvNombre.getText());
            String email = String.valueOf(tvEmail.getText());
            String description = String.valueOf(tvDescripcion.getText());

            ContentValues registro = new ContentValues();
            registro.put("name", name);
            registro.put("email", email);
            registro.put("description", description);

            int cant = bd.update("users", registro, "email='" + oldEmail +"'", null);
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, "Se actualizó con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "NO se actualizó", Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("logged_user",email);
            startActivity(intent);
        }
    }
}