package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;


    public MainActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Name");

        myRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String babyName  = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


}



    public void onClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   VideoScreen.class);
        startActivity(myIntent);
    }

    public void tempOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   TempScreen.class);
        startActivity(myIntent);
    }

    public void messOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Message.class);
        startActivity(myIntent);
    }

    public void userOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   UserProfile.class);
        startActivity(myIntent);
    }

    public void musicOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Music.class);
        startActivity(myIntent);
    }

    public void dashOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Dashboard.class);
        startActivity(myIntent);
    }

}
