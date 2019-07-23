package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.MovementMethod;
import android.util.JsonReader;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity
{

    private final String url1 = "https://swapi.co/api/films";
    private final String url2 = "https://swapi.co/api/people";


    Gson gson;
    AsyncHttpClient client;
    StarWarsResponse responseObject;
    CharacterResponse characterResponse;

    List<StarWarsResponse.ResultsBean> movieList;
    List<CharacterResponse.ResultsBean> characterList;

    ArrayList<CharacterResponse.ResultsBean> arrayCharacterList;

    TextView t;

    URL tempURL;
    String tempURLString;

    String test;

    List<CharacterResponse.ResultsBean> temp;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();



        new LoadData().execute(url1, url2);




    }

    public class LoadData extends AsyncTask<String, Integer, String>
    {

        private Exception exception;
        private ProgressBar progressBar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(100);
        }






        @Override
        protected String doInBackground(String... urls) {

            String complete = "UNDEFINED";

            try
            {

                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }



                //Initializes and declares a new Gson object which is used to map the String to our StarWarsResponse Object

                gson = new Gson();
                responseObject = gson.fromJson(builder.toString(), StarWarsResponse.class);

                //Gets only the unordered movie list

                final List<StarWarsResponse.ResultsBean> unsortedList = responseObject.getResults();

                //order unsortedList into sortedList

                Collections.sort(unsortedList, new CustomComparator());

                movieList = unsortedList;

                urlConnection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[1]);
                HttpURLConnection urlConnection2 = (HttpURLConnection) url.openConnection();

                InputStream stream2 = new BufferedInputStream(urlConnection2.getInputStream());
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(stream2));
                StringBuilder builder2 = new StringBuilder();

                String inputString2;

                while ((inputString2 = bufferedReader2.readLine()) != null)
                {
                    builder2.append(inputString2);
                }



                //Initializes and declares a new Gson object which is used to map the String to our StarWarsResponse Object

                gson = new Gson();



                characterResponse = gson.fromJson(builder2.toString(), CharacterResponse.class);




                List<CharacterResponse.ResultsBean> tempList = characterResponse.getResults();

                arrayCharacterList = new ArrayList<>(tempList);

                String nextUrl = gson.fromJson(builder2.toString(), CharacterResponse.class).getNext();

                urlConnection2.disconnect();


                tempURLString = nextUrl;
                tempURL = new URL(tempURLString);
                HttpURLConnection tempURLConnection;




                while(!(tempURLString.equals(null)))
                {

                    tempURLConnection = (HttpURLConnection) tempURL.openConnection();

                    InputStream tempStream = new BufferedInputStream(tempURLConnection.getInputStream());
                    BufferedReader tempBufferedReader = new BufferedReader(new InputStreamReader(tempStream));
                    StringBuilder tempBuilder = new StringBuilder();

                    String tempString;

                    while ((tempString = tempBufferedReader.readLine()) != null)
                    {
                        tempBuilder.append(tempString);
                    }

                    Gson gson2 = new Gson();

                    temp = gson2.fromJson(tempBuilder.toString(), CharacterResponse.class).getResults();

                    ArrayList<CharacterResponse.ResultsBean> tempArrayCharacterList = new ArrayList<>(temp);





                    arrayCharacterList.addAll(tempArrayCharacterList);

                    tempURLString =  gson.fromJson(tempBuilder.toString(), CharacterResponse.class).getNext();

                    tempURL = new URL(tempURLString);


                    tempURLConnection.disconnect();





                }




                final List<CharacterResponse.ResultsBean> unsortedList2 = new ArrayList<>(arrayCharacterList);

                characterList = unsortedList2;





            } catch (Exception e) {
                e.printStackTrace();
            }



            return complete;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }

        protected void onPostExecute(String result)
        {

            super.onPostExecute(result);

            ArrayList<StarWarsResponse.ResultsBean> arrayList = new ArrayList<>(movieList);

            t = findViewById(R.id.test);
            t.setText(arrayCharacterList.size()+ " " + temp.get(0).getName());







            Intent intent = new Intent(MainActivity.this, MovieListing.class);
            Bundle bundle = new Bundle();

            bundle.putParcelableArrayList("data", arrayList);
            intent.putExtras(bundle);

            bundle.putParcelableArrayList("characterData", arrayCharacterList);
            intent.putExtras(bundle);


            startActivity(intent);








        }
    }

    private class CustomComparator implements Comparator<StarWarsResponse.ResultsBean>
    {

        @Override
        public int compare(StarWarsResponse.ResultsBean o1, StarWarsResponse.ResultsBean o2) {
            return o1.getRelease_date().compareTo(o2.getRelease_date());
        }
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
