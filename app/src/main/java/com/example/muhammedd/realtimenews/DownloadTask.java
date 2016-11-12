package com.example.muhammedd.realtimenews;

import android.os.AsyncTask;
import android.view.View;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Muhammed Roudy on 11/11/2016.
 */

public class DownloadTask extends AsyncTask<String,Void,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


    }

    @Override
    protected String doInBackground(String... urls) {
        String result="";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {

                char current = (char) data;
                result += current;
                data = reader.read();
            }



            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}