package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MessageOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_options);
    }

    public void onClickWarning(View v){
        Intent myIntent = new Intent(getBaseContext(),   Message.class);
        startActivity(myIntent);
    }

    public void onClickNote(View v){
        Intent myIntent = new Intent(getBaseContext(),   Notifications.class);
        startActivity(myIntent);
    }
}
