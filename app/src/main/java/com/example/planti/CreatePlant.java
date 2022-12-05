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
            // checkPermissionCamera();
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
        System.out.println(data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            // Bitmap thumbnail = data.getParcelableExtra("data");
            Bundle extras = data.getExtras();
            System.out.println(extras);
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            System.out.println("Hola");
            System.out.println(imageBitmap);
            ivPlant.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100){
            if(permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                takePicture();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,101);
        System.out.println(88888);
        if(intent.resolveActivity(getPackageManager())!=null){
            System.out.println(9999);
            startActivityForResult(intent,101);

        }
    }

    /*
    private void checkPermissionCamera() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                takePicture();
                System.out.println(44444);
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},100);
                System.out.println(1111);
            }
        }else{
            System.out.println(33333);
            takePicture();
        }
    }
    */
}


