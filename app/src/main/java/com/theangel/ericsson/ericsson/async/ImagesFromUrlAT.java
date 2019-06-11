package com.theangel.ericsson.ericsson.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.theangel.ericsson.ericsson.callbacks.OnImagesDownloaded;
import com.theangel.ericsson.ericsson.model.GridItem;
import com.theangel.ericsson.ericsson.model.Show;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ImagesFromUrlAT extends AsyncTask<List<Show>, Void, List<GridItem<Show>>> {

    private static final String TAG = "c3trace";
    private OnImagesDownloaded callback;

    public ImagesFromUrlAT(OnImagesDownloaded callback) {
        this.callback = callback;
    }

    protected List<GridItem<Show>> doInBackground(List<Show>... params) {
        List<GridItem<Show>> items = new ArrayList<>();

        for (Show s: params[0]) {
            try {
                String imageUrl = s.getImage();

                Log.i(TAG, "getting image... " + imageUrl);

                URL tunnel = new URL(imageUrl);
                InputStream imageBytes = tunnel.openConnection().getInputStream();

                Bitmap image = BitmapFactory.decodeStream(imageBytes);

                items.add(new GridItem<Show>(s, image));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    @Override
    protected void onPostExecute(List<GridItem<Show>> items) {
        super.onPostExecute(items);

        if (callback != null) {
            callback.onImagesDownloaded(items);
        }
    }
}
