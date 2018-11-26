package com.pkinsellaweb.BabyMate;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                 .withFullScreen()
                 .withTargetActivity(MainActivity.class)
                 .withSplashTimeOut(5000)
                 .withBackgroundColor(Color.parseColor("#F88012"))
                 .withLogo(R.drawable.splashlogo)
                 .withHeaderText("Baby Mate")
                 .withFooterText("Copyright pkinsellaweb.com 2018")
                 .withBeforeLogoText("Paul Kinsella")
                 .withAfterLogoText("4th Year BSc Computing - NCI");

         //Set the color to white
        config.getHeaderTextView().setTextColor(Color.WHITE);
        config.getHeaderTextView().setTextSize(24);
        config.getFooterTextView().setTextColor(Color.WHITE);
        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextColor(Color.WHITE);

        //set to view
        View view = config.create();

        setContentView(view);
    }
}
