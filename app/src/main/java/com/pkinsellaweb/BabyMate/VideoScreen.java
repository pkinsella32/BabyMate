package com.pkinsellaweb.BabyMate;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_video_screen);



        VideoView video = (VideoView) findViewById(R.id.videoView);


//        String vidAdd = "http://192.168.43.66:8160/";
//        Uri videoPath = Uri.parse(vidAdd);
        Uri videoPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.baby);
        video.setVideoURI(videoPath);

        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.start();
    }
}
