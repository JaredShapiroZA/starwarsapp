package com.example.starwarsappdemo;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoadData extends AsyncTask<String, Void, StarWarsResponse>
{

    private Exception exception;





    @Override
    protected StarWarsResponse doInBackground(String... urls) {

        try {
            final int OK = 200;
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            int responseCode = connection.getResponseCode();

            if (responseCode == OK) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String content = response.toString();


                Gson gson = new GsonBuilder().registerTypeAdapter(StarWarsResponse.class, new StarWarsResponse())
                        .serializeNulls().create();

                StarWarsResponse result = gson.fromJson(content, new TypeToken<StarWarsResponse>(){}.getType());

                return result;



            }

        }

        catch(Exception e)
        {
            this.exception = e;

            return null;
        }





        return null;
    }





    protected void onPostExecute(StarWarsResponse result)
    {

        super.onPostExecute(result);



    }
}
