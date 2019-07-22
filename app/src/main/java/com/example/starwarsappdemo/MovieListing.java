package com.example.starwarsappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovieListing extends AppCompatActivity {

    public ListView listView;
    StarWarsResponse responseObject;
    CustomAdapter adapter;
    Gson gson;

    private static int choice;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies);

        //binds the data to the list

        Intent intent = getIntent();

        String responseString = intent.getStringExtra("data");

        gson = new Gson();
        responseObject = gson.fromJson(responseString, StarWarsResponse.class);

        List<StarWarsResponse.ResultsBean> unsortedList = responseObject.getResults();

        //order unsortedList into sortedList

        Collections.sort(unsortedList, new CustomComparator());






        adapter = new CustomAdapter(MovieListing.this, unsortedList);

        listView = findViewById(R.id.movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                choice = i;

                Toast.makeText(MovieListing.this, choice+"", Toast.LENGTH_SHORT).show();


                //Send through the StarWarsResponse ResultsBean Object that corresponds to the choice

                Intent intent = new Intent(view.getContext(), FilmEntry.class);
                startActivity(intent);



            }
        });


    }

    private class CustomComparator implements Comparator<StarWarsResponse.ResultsBean>
    {

        @Override
        public int compare(StarWarsResponse.ResultsBean o1, StarWarsResponse.ResultsBean o2) {
            return o1.getRelease_date().compareTo(o2.getRelease_date());
        }
    }


}
