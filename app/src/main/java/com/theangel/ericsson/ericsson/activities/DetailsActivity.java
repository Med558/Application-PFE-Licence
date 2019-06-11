package com.theangel.ericsson.ericsson.activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.theangel.ericsson.R;
import com.theangel.ericsson.ericsson.async.ImageFromUrlAT;
import com.theangel.ericsson.ericsson.async.OneShowAT;
import com.theangel.ericsson.ericsson.callbacks.OnImageDownloaded;
import com.theangel.ericsson.ericsson.callbacks.OnImagesDownloaded;
import com.theangel.ericsson.ericsson.callbacks.OnOneShowSelected;
import com.theangel.ericsson.ericsson.model.GridItem;
import com.theangel.ericsson.ericsson.model.Show;

import java.util.List;

public class DetailsActivity extends AppCompatActivity implements OnOneShowSelected, OnImageDownloaded {

    private final String TAG = "c3trace";

    private TextView tvName;
    private ImageView imIllustation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvName = (TextView) findViewById(R.id.tvName);
        imIllustation = (ImageView) findViewById(R.id.imIllustration);

        int id = (int) getIntent().getLongExtra("id", -1);
        Log.i(TAG, "id: " + id);
        if (id != -1) {
            OneShowAT async = new OneShowAT(this);
            async.execute(id);
        }
    }


    @Override
    public void onImageDownloaded(Bitmap bitmap) {
        imIllustation.setImageBitmap(bitmap);
    }

    @Override
    public void onOneShowSeleted(Show show) {
        Log.i(TAG, show.toString());

        tvName.setText(show.getName());
        ImageFromUrlAT async = new ImageFromUrlAT(this);
        async.execute(show.getImage());
    }
}
