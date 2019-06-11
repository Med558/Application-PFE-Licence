package com.theangel.ericsson.ericsson.async;

import android.os.AsyncTask;
import android.util.Log;

import com.theangel.ericsson.ericsson.callbacks.OnOneShowSelected;
import com.theangel.ericsson.ericsson.model.Show;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;


public class OneShowAT extends AsyncTask<Integer, Void, Show> {
    private String baseUrl = "http://api.tvmaze.com/shows/";
    private final String TAG = "c3trace";

    private OnOneShowSelected callback;

    public OneShowAT(OnOneShowSelected callback) {
        this.callback = callback;
    }

    @Override
    protected Show doInBackground(Integer... params) {
        Show show = null;

        int id = params[0];
        String fullUrl = baseUrl + id;

        Log.i(TAG, fullUrl);

        try {
            String json = WSUtils.getJson(fullUrl);

            JSONObject jsonShow = new JSONObject(json);

            show = WSUtils.parseShow(jsonShow);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return show;
    }

    @Override
    protected void onPostExecute(Show show) {
        super.onPostExecute(show);

        if(callback != null){
            callback.onOneShowSeleted(show);
        }
    }
}
