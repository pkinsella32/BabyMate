package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    ImageView splashImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefStatus = database.getReference("Status");
        myRefStatus.setValue(0);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.splash);
        splashImage = findViewById(R.id.splashimage);
        splashImage.setAnimation(animation);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },5000);
//         EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
//                 .withFullScreen()
//                 .withTargetActivity(MainActivity.class)
//                 .withSplashTimeOut(5000)
//                 .withBackgroundColor(Color.parseColor("#F88012"))
//                 .withLogo(R.drawable.baby_crying)
//                 .withHeaderText("BabyMate")
//                 .withFooterText("Copyright pkinsellaweb.com 2018")
//                 .withBeforeLogoText("Baby Monitoring System")
//                 .withAfterLogoText("4th Year BSc Computing - NCI");
//
//         //Set the color to white
//        config.getHeaderTextView().setTextColor(Color.WHITE);
//        config.getHeaderTextView().setTextSize(54);
//        config.getFooterTextView().setTextColor(Color.WHITE);
//        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
//        config.getBeforeLogoTextView().setTextSize(20);
//        config.getAfterLogoTextView().setTextColor(Color.WHITE);
//
//        //set to view
//        View view = config.create();
//
//        setContentView(view);
    }
}
