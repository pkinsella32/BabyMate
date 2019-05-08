package com.pkinsellaweb.babyMate;

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

    }

    public void twinkleOnClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSongChoice  = database.getReference("SongChoice");
        myRefSongChoice .setValue(2);
        Toast.makeText(getBaseContext(), "Twinkle Twinkle Little Star Selected" , Toast.LENGTH_SHORT ).show();

    }

    public void sharkOnClick(View v){
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

    public void hushOnClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSpider = database.getReference("SongChoice");
        myRefSpider.setValue(4);
        Toast.makeText(getBaseContext(), "Hush Little Baby Selected" , Toast.LENGTH_SHORT ).show();
    }

    public void mumOnClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSpider = database.getReference("SongChoice");
        myRefSpider.setValue(5);
        Toast.makeText(getBaseContext(), "Mum's Voice Selected" , Toast.LENGTH_SHORT ).show();
    }

    public void dadOnClick(View v){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefSpider = database.getReference("SongChoice");
        myRefSpider.setValue(6);
        Toast.makeText(getBaseContext(), "Dad's Voice Selected" , Toast.LENGTH_SHORT ).show();
    }
}
