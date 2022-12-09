package com.example.planti;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;


public class CreatePlant extends AppCompatActivity implements View.OnClickListener {

    Button btnCrear,btnTomarFoto;
    EditText etNombre, etTipo, etDescripcion;
    ImageView ivPlant;

    Bdsqlite admin;
    SQLiteDatabase bd;

    int id;
    String name;
    String plantKind;
    Bitmap imageBitmap;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plant);
        btnCrear = findViewById(R.id.btnCrear);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        ivPlant = findViewById(R.id.ivPlant);
        etNombre = findViewById(R.id.etNombre);
        etTipo = findViewById(R.id.etTipo);
        etDescripcion = findViewById(R.id.etDescripcion);

        btnCrear.setOnClickListener(this);
        btnTomarFoto.setOnClickListener(this);

        admin = new Bdsqlite(this, "planti", null, 1);
        bd = admin.getWritableDatabase();
        getData();
    }

    private void getData() {
        id = countPlants();
        name = String.valueOf(etNombre.getText());
        plantKind = String.valueOf(etTipo.getText());
        description = String.valueOf(etDescripcion.getText());
    }

    private int countPlants() {
        Cursor fila = bd.rawQuery("select count(*) from plants", null);
        if (fila.moveToFirst()) {
            String result = fila.getString(0);
            return Integer.parseInt(result) + 1;
        } else {
            return 1;
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnTomarFoto){
            takePicture();
        }
        else if (view == btnCrear) {
            getData();
            if(id == -1){
                Toast.makeText(this, "Ocurrió un error al intentar crear el usuario", Toast.LENGTH_LONG).show();
            }else if(imageBitmap == null){
                Toast.makeText(this, "Debes tomar una foto de tu planta para publicarla", Toast.LENGTH_LONG).show();
            }else{
                ContentValues registro = new ContentValues();
                registro.put("id", id);
                registro.put("name", name);
                registro.put("plantKind", plantKind);
                registro.put("imageBitmap", getBytes(imageBitmap));
                registro.put("description", description);

                bd.insert("plants", null, registro);
                Toast.makeText(this, "Se creó la planta satisfactoriamente", Toast.LENGTH_LONG).show();

                Bundle b = this.getIntent().getExtras();
                String email = b.getString("logged_user");

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("logged_user",email);
                startActivity(intent);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            ivPlant.setImageBitmap(imageBitmap);
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,101);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}


