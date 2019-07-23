package com.example.starwarsappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
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

        Bundle extras = getIntent().getExtras();
        ArrayList arrayList = null;

        if (extras != null) {
            arrayList = extras.getParcelableArrayList("data");

        }
        else{
            Toast.makeText(this, "DIDNT WORK", Toast.LENGTH_SHORT).show();
        }

        final List<StarWarsResponse.ResultsBean> resultList = arrayList;


        //Makes an adapter based on this Context and the now sorted list

        adapter = new CustomAdapter(MovieListing.this, resultList);

        //Initializes the list view and sets the adapter to it

        listView = findViewById(R.id.movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                choice = i;

                //Send through the StarWarsResponse ResultsBean Object that corresponds to the choice


                StarWarsResponse.ResultsBean movieChoice = resultList.get(choice);

                Toast.makeText(MovieListing.this, movieChoice.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), FilmEntry.class);
                intent.putExtra("object", movieChoice);
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
