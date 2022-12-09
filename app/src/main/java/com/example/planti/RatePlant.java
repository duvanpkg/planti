package com.example.planti;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RatePlant extends AppCompatActivity implements View.OnClickListener {
    RatingBar rtgBar;
    Button btnComent;
    TextView mensajeTextView;
    EditText comentarioEditText;
    float rate;

    Bdsqlite admin;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_plant);
        mensajeTextView = findViewById(R.id.mensajeTextView);
        mensajeTextView.setText("¡Lo mas visto de hoy!");
        rtgBar = (RatingBar) findViewById(R.id.rtgBar);
        rtgBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rate = rating;
                Toast.makeText(RatePlant.this, "Usted ha votado con: " + rating, Toast.LENGTH_LONG).show();
            }
        });
        btnComent = findViewById(R.id.btnComent);
        btnComent.setOnClickListener(this);

        admin = new Bdsqlite(this, "planti", null, 1);
        bd = admin.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        Bundle b = this.getIntent().getExtras();
        String email = b.getString("logged_user");
        int idPlant = b.getInt("idPlant");

        float newRatingAcum;
        int newTimesRated;

        Cursor fila = bd.rawQuery("select ratingAcum, timesRated from plants where id="+idPlant, null);
        if (fila.moveToFirst()) {
            @SuppressLint("Range")
            float oldRatingAcum = fila.getFloat(fila.getColumnIndex("ratingAcum"));
            @SuppressLint("Range")
            int oldTimesRated = fila.getInt(fila.getColumnIndex("timesRated"));

            newRatingAcum = oldRatingAcum + rate;
            newTimesRated = oldTimesRated + 1;
            ContentValues registro = new ContentValues();
            registro.put("ratingAcum", newRatingAcum);
            registro.put("timesRated", newTimesRated);
            int cant = bd.update("plants", registro, "id=" + idPlant, null);
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, "Se registró con éxito", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "NO se resgistró", Toast.LENGTH_LONG).show();
            }
        }



        comentarioEditText = findViewById(R.id.comentarioEditText);
        String mensajesString = comentarioEditText.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("logged_user",email);
        startActivity(intent);
    }
}

