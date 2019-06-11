package com.theangel.ericsson.ericsson.async;

import com.theangel.ericsson.ericsson.model.Show;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class WSUtils {
    public static String getJson(String fullUrl) throws IOException {

        URL webservice = new URL(fullUrl);

        HttpURLConnection connection = (HttpURLConnection) webservice.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        StringBuilder builder = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        String json = builder.toString();

        return json;
    }

    public static Show parseShow(JSONObject jsonShow) throws JSONException {

        int id = jsonShow.getInt("id");
        String name = jsonShow.getString("name");
        int runtime = jsonShow.getInt("runtime");
        String summary = jsonShow.getString("summary");

        String image = "https://lh5.ggpht.com/cr6L4oleXlecZQBbM1EfxtGggxpRK0Q1cQ8JBtLjJdeUrqDnXAeBHU30trRRnMUFfSo=w300";

        if(jsonShow.has("image") && !jsonShow.isNull("image")) {
            JSONObject jsonImage = jsonShow.getJSONObject("image");
            if (jsonImage != null) {
                image = jsonImage.getString("original");
            }
        }

        double average = -1;
        if(jsonShow.has("rating") && !jsonShow.isNull("rating")) {
            JSONObject jsonRating = jsonShow.getJSONObject("rating");
            if(jsonRating.has("average") && !jsonRating.isNull("average")) {
                average = jsonRating.getDouble("average");
            }
        }

        Show show = new Show(id, name, runtime, image, summary, average);

        return  show;
    }
}
