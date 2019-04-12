package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class TempScreen extends AppCompatActivity {
   // private ArrayList<String> mTemp = new ArrayList<>();
    private ListView mListView;
    private TextView htextView;
    private TextView tTextView;
    private TextView airTextView;
    private TextView lightView;
    private TextView motionView;
    private TextView soundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_screen);

//         mListView = (ListView) findViewById(R.id.tempList);
         htextView = (TextView) findViewById(R.id.humidView);
         tTextView = (TextView) findViewById(R.id.tempScreenView);
         airTextView = (TextView) findViewById(R.id.airQualityView);
         lightView = (TextView) findViewById(R.id.lightInfoView);
         motionView = (TextView) findViewById(R.id.motionInfoView);
         soundView = (TextView) findViewById(R.id.soundInfoView);

//        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mTemp);
//        mListView.setAdapter(arrayAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefHumid = database.getReference("Humid");
        DatabaseReference myRefAir = database.getReference("AirStatus");
        DatabaseReference myRefLight = database.getReference("Light");
        DatabaseReference myRefMotion = database.getReference("Movement");
        DatabaseReference myRefSound = database.getReference("Sound");

        myRefSound.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer soundValue = dataSnapshot.getValue(Integer.class);
                String lightString = Integer.toString(soundValue);
                if(soundValue > 300){
                    soundView.setText("Sound Levels High");
                }else if((soundValue < 300)){
                    soundView.setText("Sound Levels Normal");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRefMotion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer motionValue = dataSnapshot.getValue(Integer.class);
                String lightString = Integer.toString(motionValue);
                if(motionValue < 20){
                    motionView.setText("No Movement Detected");
                }else if((motionValue > 20)){
                    motionView.setText("Movement Detected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRefLight.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer lightValue = dataSnapshot.getValue(Integer.class);
                String lightString = Integer.toString(lightValue);
                if(lightValue > 900) {
                    lightView.setText("Light levels are High:");
                }else if((lightValue < 400)){
                    lightView.setText("Light levels are Low:");
                }else{
                    lightView.setText("Light levels are Normal:");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRefAir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer airValue = dataSnapshot.getValue(Integer.class);
//                mTemp.add("Air Quality is: " + airValue);
//                arrayAdapter.notifyDataSetChanged();
                String airString = Integer.toString(airValue);
                if(airValue < 300){
                    airTextView.setText("Air Quality is: Good");
                }
                if(airValue > 700){
                    airTextView.setText("Air Quality is: Bad");
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });



        myRefHumid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer humidValue = dataSnapshot.getValue(Integer.class);
//                mTemp.add("Room Humidity is: " + humidValue);
//                arrayAdapter.notifyDataSetChanged();
                String humidString = Integer.toString(humidValue);
                htextView.setText("Humidity:" + " \n"+ humidString+"%");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
        myRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer tempValue = dataSnapshot.getValue(Integer.class);
//                mTemp.add("Room Temperature is: " +tempValue+"c");
                String tempString = Integer.toString(tempValue);
                tTextView.setText("Temp:" + " \n" +tempString+"c");
//                arrayAdapter.notifyDataSetChanged();
                Log.d("TAG", "Value is: " + tempValue);

//                if((tempValue) <= 15){
//                    mTemp.add("The room is currently very cold " +tempValue+"c"+ " Consider turning up the heat.");
//                }
//                else if((tempValue) <= 18){
//                    mTemp.add("The room is currently chilly " +tempValue+"c");
//                }
//
//                else if((tempValue) >= 22){
//                    mTemp.add("The room is currently very warm " +tempValue+"c"+" turn down the heat!");
//                }
//                else{
//                    mTemp.add("The room is at a comfortable temperature");
//                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


    }
    public void onClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   VideoScreen.class);
        startActivity(myIntent);
    }

    public void tempOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   VideoScreen.class);
        startActivity(myIntent);
    }

}
