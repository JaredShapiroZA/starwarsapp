package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_entry);

        entryTitle = findViewById(R.id.entryTitle);
        entryDate = findViewById(R.id.entryDate);
        imdbScore = findViewById(R.id.imdbScore);
        entryCharacters = findViewById(R.id.entryCharacters);

        Intent intent = getIntent();

        StarWarsResponse.ResultsBean film = intent.getParcelableExtra("object");

        ImdbResponse imdbResponse = intent.getParcelableExtra("rating");

        double rating = imdbResponse.getRating();

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

                if (!(characterText.equals("")))
                characterText = characterText + ", ";


                if (i==0)
                {
                    characterText = resultList.get(i).getName();
                }
                else
                {
                    characterText = characterText + resultList.get(i).getName();
                }

            }
        }




        entryTitle.setText(film.getTitle());
        entryDate.setText(film.getRelease_date());
        entryCharacters.setText(characterText);

        if (rating<=2)
        {
            imdbScore.setImageResource(R.drawable.star1);
        }
        else if(rating>2 && rating<=4)
        {
            imdbScore.setImageResource(R.drawable.star2);
        }
        else if (rating>4 && rating<=6)
        {
            imdbScore.setImageResource(R.drawable.star3);
        }
        else if (rating>6 && rating<=8)
        {
            imdbScore.setImageResource(R.drawable.star4);
        }
        else
        {
            imdbScore.setImageResource(R.drawable.star5);
        }

        ConstraintLayout layout = findViewById(R.id.layoutAnim);

        String[] crawlLines = film.getOpening_crawl().split("\n");

        String temp;

        for(int i = 0; i<crawlLines.length; i++)
        {
            temp = crawlLines[i];

            final TextView dynamicTextView = new TextView(this);

            dynamicTextView.setText(temp);

            dynamicTextView.setTypeface(null, Typeface.BOLD);

            dynamicTextView.setAllCaps(true);

            //dynamicTextView.setTextSize(20);

            dynamicTextView.setPadding(10,0,0,0);

            dynamicTextView.setGravity(Gravity.CENTER_VERTICAL);

            layout.addView(dynamicTextView);

            ObjectAnimator tempFlipAnimation = ObjectAnimator.ofFloat(dynamicTextView, "rotationX", 0.0f, 45f);
            tempFlipAnimation.setDuration(1);
            tempFlipAnimation.setRepeatCount(0);
            tempFlipAnimation.setStartDelay(1);

            Animation tempAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationfile);

            tempAnimation.setStartOffset(i*500);

            tempAnimation.setInterpolator(new LinearInterpolator());

            dynamicTextView.startAnimation(tempAnimation);

            tempFlipAnimation.start();

        }

    }
}
