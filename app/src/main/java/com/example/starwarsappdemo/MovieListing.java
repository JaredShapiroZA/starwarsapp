package com.example.starwarsappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

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


        adapter = new CustomAdapter(MovieListing.this, responseObject.getResults());

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

}
