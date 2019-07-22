package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FilmEntry extends AppCompatActivity {

    TextView entryTitle;
    TextView entryDate;
    ImageView imdbScore;
    TextView entryCharacters;
    TextView entryCrawlingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_entry);

        entryTitle = findViewById(R.id.entryTitle);
        entryDate = findViewById(R.id.entryDate);
        imdbScore = findViewById(R.id.imdbScore);
        entryCharacters = findViewById(R.id.entryCharacters);
        entryCrawlingText = findViewById(R.id.entryCrawlingText);

        Intent intent = getIntent();

        StarWarsResponse.ResultsBean film = intent.getParcelableExtra("object");

        entryTitle.setText(film.getTitle());
        entryDate.setText(film.getRelease_date());
        //get imdb score
        entryCharacters.setText(film.getCharacters().toString());
        entryCrawlingText.setText(film.getOpening_crawl());






    }
}
