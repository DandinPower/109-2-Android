package com.example.a108590045_hw10;

import android.content.Context;
import android.net.Uri;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    static String getSource(String queryString,String type) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String htmlSource = null;

        try {
            Uri builtURI;
            if (type.equals("http://"))
                builtURI = Uri.parse(queryString).buildUpon().scheme(HTTP).build();
            else
                builtURI = Uri.parse(queryString).buildUpon().scheme(HTTPS).build();

            URL requestURL = new URL(builtURI.toString());

            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }

            if (builder.length() == 0) return null;

            htmlSource = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) urlConnection.disconnect();

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return htmlSource;
    }
}