package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class FilmEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_entry);

        Intent intent = getIntent();

        StarWarsResponse.ResultsBean delivery = intent.getParcelableExtra("object");

        TextView t = findViewById(R.id.test);

        Toast.makeText(FilmEntry.this, delivery.getRelease_date(), Toast.LENGTH_SHORT).show();






    }
}
