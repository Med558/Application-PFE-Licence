package com.theangel.ericsson.ericsson.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.theangel.ericsson.ericsson.callbacks.OnImageDownloaded;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;



public class ImageFromUrlAT extends AsyncTask<String, Void, Bitmap> {

    private OnImageDownloaded callback;

    public ImageFromUrlAT(OnImageDownloaded callback) {
        this.callback = callback;
    }

    protected Bitmap doInBackground(String... params) {

        Bitmap image = null;

        try {
            String imageUrl = params[0];

            URL tunnel = new URL(imageUrl);
            InputStream imageBytes = tunnel.openConnection().getInputStream();

            image = BitmapFactory.decodeStream(imageBytes);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(callback != null){
            callback.onImageDownloaded(bitmap);
        }
    }
}
