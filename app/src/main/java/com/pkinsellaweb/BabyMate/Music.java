package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Music extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSong1 = database.getReference("SongOne");
        myRefSong1.setValue(0);
    }

    public void onClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSong1 = database.getReference("SongOne");
        myRefSong1.setValue(1);
        Toast.makeText(getBaseContext(), "Son1 Selected" , Toast.LENGTH_SHORT ).show();
    }
}
