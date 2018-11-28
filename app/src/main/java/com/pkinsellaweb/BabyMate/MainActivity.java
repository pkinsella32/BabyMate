package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent myIntent = new Intent(getBaseContext(),   MessageScreen.class);
        startActivity(myIntent);
    }
}
