package com.example.starwarsappdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity
{

    private final String url1 = "https://swapi.co/api/films";
    private final String url2 = "https://swapi.co/api/people";
    private final String film1 = "http://www.omdbapi.com/?t=Star+Wars+Episode+I&apikey=47b256cc";
    private final String film2 = "http://www.omdbapi.com/?t=Star+Wars+Episode+II&apikey=47b256cc";
    private final String film3 = "http://www.omdbapi.com/?t=Star+Wars+Episode+III&apikey=47b256cc";
    private final String film4 = "http://www.omdbapi.com/?t=Star+Wars+Episode+IV&apikey=47b256cc";
    private final String film5 = "http://www.omdbapi.com/?t=Star+Wars+Episode+V&apikey=47b256cc";
    private final String film6 = "http://www.omdbapi.com/?t=Star+Wars+Episode+VI&apikey=47b256cc";
    private final String film7 = "http://www.omdbapi.com/?t=Star+Wars+Episode+VII&apikey=47b256cc";

    private Gson gson;

    private StarWarsResponse responseObject;
    private CharacterResponse characterResponse;

    private List<StarWarsResponse.ResultsBean> movieList;

    private ArrayList<CharacterResponse.ResultsBean> arrayCharacterList;
    private ArrayList<ImdbResponse> imdbResponseList;

    private URL tempURL;
    private String tempURLString;

    private ProgressBar mProgressBar;

    private List<CharacterResponse.ResultsBean> temp;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progressBar);

        imdbResponseList = new ArrayList<>();

        new LoadData().execute(url1, url2, film1, film2, film3, film4, film5, film6, film7);

    }

    public class LoadData extends AsyncTask<String, Integer, String>
    {

        private Exception exception;
        private ProgressBar progressBar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setMax(17);
        }


        @Override
        protected String doInBackground(String... urls) {

            String complete = "";

            int progress = 0;

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

                progress++;
                publishProgress(progress);

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

                progress++;
                publishProgress(progress);

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

                    if(tempURLString!=null) {
                        tempURL = new URL(tempURLString);
                    }

                    progress++;
                    publishProgress(progress);

                    tempURLConnection.disconnect();

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[2]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }


                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";

                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);
                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[3]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }

                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";

                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);

                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[4]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }

                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";

                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);

                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[5]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }

                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";

                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);

                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[6]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }

                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";

                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);

                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[7]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }

                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";

                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);

                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }

            try
            {

                URL url = new URL(urls[8]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;

                while ((inputString = bufferedReader.readLine()) != null)
                {
                    builder.append(inputString);
                }


                JSONObject film = new JSONObject(builder.toString());

                double rating = Double.parseDouble(film.get("imdbRating")+"");
                String title = film.get("Title")+"";



                ImdbResponse response = new ImdbResponse(rating, title);

                imdbResponseList.add(response);

                urlConnection.disconnect();

                progress++;
                publishProgress(progress);

            } catch (Exception e) {
                e.printStackTrace();
            }


            return complete;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {

            mProgressBar.setProgress(values[0]);

        }

        protected void onPostExecute(String result)
        {

            super.onPostExecute(result);

            ArrayList<StarWarsResponse.ResultsBean> arrayList = new ArrayList<>(movieList);

            Intent intent = new Intent(MainActivity.this, MovieListing.class);
            Bundle bundle = new Bundle();

            bundle.putParcelableArrayList("data", arrayList);
            intent.putExtras(bundle);

            bundle.putParcelableArrayList("characterData", arrayCharacterList);
            intent.putExtras(bundle);

            bundle.putParcelableArrayList("ratingData", imdbResponseList);
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

    }


    @Override
    protected void onResume()
    {
        super.onResume();

    }

    @Override
    protected void onPause()
    {
        super.onPause();

    }

    @Override
    protected void onStop()
    {
        super.onStop();

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
