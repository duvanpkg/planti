package com.example.planti;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.planti.ui.main.HomeFragment;


public class CreatePlant extends AppCompatActivity implements View.OnClickListener {

    Button btnCrear,btnTomarFoto;
    EditText etNombre, etTipo, etDescripcion;
    ImageView ivPlant;

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
    }

    @Override
    public void onClick(View view) {
        if(view == btnTomarFoto){
            takePicture();
        }
        else if (view == btnCrear) {
            // todo: guardar info de la planta en la base de datos
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPlant.setImageBitmap(imageBitmap);
        }
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,101);
    }
}


