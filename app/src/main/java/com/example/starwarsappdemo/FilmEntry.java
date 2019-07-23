package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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

        Bundle extras = getIntent().getExtras();
        ArrayList characterArrayList = null;

        if (extras != null) {

            characterArrayList = extras.getParcelableArrayList("characterData");

        }
        else{
            Toast.makeText(this, "DIDNT WORK", Toast.LENGTH_SHORT).show();
        }


        final List<CharacterResponse.ResultsBean> resultList = characterArrayList;

        String characterUrls = film.getCharacters().toString();

        String characterText = "";

        for(int i = 0; i<resultList.size(); i++)
        {
            if(characterUrls.contains(resultList.get(i).getUrl()))
            {
                if (i==0)
                {
                    characterText = resultList.get(i).getName();
                }
                else
                {
                    characterText = characterText + ", " + resultList.get(i).getName();
                }

            }
        }



        entryTitle.setText(film.getTitle());
        entryDate.setText(film.getRelease_date());
        //get imdb score
        entryCharacters.setText(characterText);
        entryCrawlingText.setText(film.getOpening_crawl());






    }
}
