package com.example.planti;

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
                Toast.makeText(RatePlant.this, "Usted ha votado con: " + rating, Toast.LENGTH_LONG).show();
            }
        });
        btnComent = findViewById(R.id.btnComent);
        btnComent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i("Info", "Boton presionado");

        comentarioEditText = findViewById(R.id.comentarioEditText);
        String mensajesString = comentarioEditText.getText().toString();
        // save comment to database and rating to database
    }
}

