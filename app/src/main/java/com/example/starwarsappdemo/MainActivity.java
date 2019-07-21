package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.util.JsonReader;
import android.widget.ListView;
import android.widget.Toast;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity
{

    private final String url = "https://swapi.co/api/films";
    Gson gson;
    AsyncHttpClient client;

    TextView t1;








    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();




        client = new AsyncHttpClient();

        client.get(MainActivity.this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = new String(responseBody);

                //t1 = findViewById(R.id.textView);

                //t1.setText(responseString);



                //gson = new Gson();
                //responseObject = gson.fromJson(responseString, StarWarsResponse.class);


                //adapter = new CustomAdapter(MainActivity.this, responseObject.getResults());
                //listview.setAdapter(adapter);

                Intent intent = new Intent(MainActivity.this, MovieListing.class);
                intent.putExtra("data", responseString);

                startActivity(intent);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {



            }
        });




        //new LoadData().execute(url);





    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume()
    {
        super.onResume();

        Toast.makeText(this, "Resumed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }








}
