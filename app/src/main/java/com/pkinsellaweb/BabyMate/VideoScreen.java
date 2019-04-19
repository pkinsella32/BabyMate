package com.pkinsellaweb.BabyMate;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoScreen extends AppCompatActivity {
    //private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_video_screen);


        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://youtu.be/V_X3ofNHJ7I");
       // webView.loadUrl("https://youtu.be/WSaumwdFWuk");


       // VideoView video = (VideoView) findViewById(R.id.videoView);
       // TextView text = (TextView) findViewById(R.id.videoText);

//        String vidAdd = "http://192.168.43.66:8160";
//        Uri videoPath = Uri.parse(vidAdd);
////        Uri videoPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.baby);
//        video.setVideoURI(videoPath);
//
//        MediaController mediaController = new
//                MediaController(this);
//        mediaController.setAnchorView(video);
//        video.setMediaController(mediaController);
//        video.start();
   }
}
