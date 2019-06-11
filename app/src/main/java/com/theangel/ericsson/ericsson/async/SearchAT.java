package com.theangel.ericsson.ericsson.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.theangel.ericsson.ericsson.callbacks.OnSearchCompleted;
import com.theangel.ericsson.ericsson.model.Show;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


public class SearchAT extends AsyncTask<String, Void, List<Show>> {
    private final String TAG = "c3trace";
    private String baseUrl = "http://api.tvmaze.com/search/shows?q=";

    private OnSearchCompleted callback;

    public SearchAT(OnSearchCompleted callback) {
        this.callback = callback;
    }

    @Override
    protected List<Show> doInBackground(String... params) {
        List<Show> shows = new ArrayList<>();

        String showName = params[0];
        String fullUrl = baseUrl + showName;

        Log.i(TAG, fullUrl);

        try {
            String json = WSUtils.getJson(fullUrl);

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject jsonShow = jsonObject.getJSONObject("show");

                Show show = WSUtils.parseShow(jsonShow);
                shows.add(show);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return shows;
    }

    @Override
    protected void onPostExecute(List<Show> shows) {
        super.onPostExecute(shows);

        if(callback != null){
            callback.onSearchCompleted(shows);
        }
    }
}
