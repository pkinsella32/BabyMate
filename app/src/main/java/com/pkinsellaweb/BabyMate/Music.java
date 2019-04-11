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
        DatabaseReference myRefSongChoice = database.getReference("SongChoice");
        myRefSongChoice .setValue(0);
//        DatabaseReference myRefBabyShark = database.getReference("SongTwo");
//        myRefBabyShark.setValue(0);
//        DatabaseReference myRefSpider = database.getReference("SongThree");
//        myRefSpider.setValue(0);
    }

    public void song2OnClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSongChoice  = database.getReference("SongChoice");
        myRefSongChoice .setValue(2);
        Toast.makeText(getBaseContext(), "Twinkle Twinkle Little Star Selected" , Toast.LENGTH_SHORT ).show();

    }

    public void onClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSongChoice  = database.getReference("SongChoice");
        myRefSongChoice .setValue(1);
        Toast.makeText(getBaseContext(), "Baby Shark  Selected" , Toast.LENGTH_SHORT ).show();
    }

    public void spiderOnClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSpider = database.getReference("SongChoice");
        myRefSpider.setValue(3);
        Toast.makeText(getBaseContext(), "Incy Winy Spider Selected" , Toast.LENGTH_SHORT ).show();
    }
}
