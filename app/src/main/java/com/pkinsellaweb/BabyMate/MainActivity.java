package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



}



    public void onClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   VideoScreen.class);
        startActivity(myIntent);
    }

    public void stratOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Temp.class);
        startActivity(myIntent);
    }

    public void mmdOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Message.class);
        startActivity(myIntent);
    }
}
