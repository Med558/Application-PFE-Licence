package com.theangel.ericsson.ericsson.activities;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.theangel.ericsson.R;

//import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;


public class YoutubeVideo1Activity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubeView1;
    String lien_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video1);

        //Make sure you initialize youtube player

        youTubeView1 = (YouTubePlayerView) findViewById(R.id.video_view);

        youTubeView1.initialize("AIzaSyDmHT0xby_VUR13Jfp9fQVROqZYP_UCgbI", this);
        lien_video = getIntent().getStringExtra("lien_video");


    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(lien_video);
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Oh no! erreur",Toast.LENGTH_LONG).show();
    }
}
