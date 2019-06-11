package com.theangel.ericsson.ericsson.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.theangel.ericsson.R;

public class Apercu extends AppCompatActivity {

    TextView txt_nom_radio;
    MediaPlayer mediaPlayer;
    final Context context = this;
    Button btn;
    private static ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apercu);

        Intent intent = getIntent();
        String lien = intent.getStringExtra("cle_lien");
        String nom = intent.getStringExtra("cle_nom");
        txt_nom_radio = (TextView)findViewById(R.id.nom_radio);
        txt_nom_radio.setText(nom);

        btn = (Button)findViewById(R.id.play_pause_btn);
        btn.setBackgroundResource(R.drawable.pause_noir);

        // Create a progressbar
        pDialog = new ProgressDialog(Apercu.this);
        // Set progressbar title
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        // Show progressbar
        pDialog.show();

        mediaPlayer = MediaPlayer.create(this, Uri.parse(lien));

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                mediaPlayer.start();
            }
        });
    }

    public void play_btn_click (View view) {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            btn.setBackgroundResource(R.drawable.play_noir);
        }else {
            mediaPlayer.start();
            btn.setBackgroundResource(R.drawable.pause_noir);
        }
    }

    @Override
    public void finish() {

        mediaPlayer.stop();
        super.finish();
    }
}
