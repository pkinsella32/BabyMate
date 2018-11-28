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

        Uri videoPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.baby);
        video.setVideoURI(videoPath);

        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.start();
    }
}
