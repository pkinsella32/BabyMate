package com.pkinsellaweb.babyMate;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TempScreen extends AppCompatActivity {
    private ListView mListView;
    private TextView htextView;
    private TextView tTextView;
    private TextView airTextView;
    private TextView lightView;
    private TextView motionView;
    private TextView motionView2;
    private TextView soundView;
    private TextView soundView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_screen);

         htextView = (TextView) findViewById(R.id.humidView);
         tTextView = (TextView) findViewById(R.id.tempScreenView);
         airTextView = (TextView) findViewById(R.id.airQualityView);
         lightView = (TextView) findViewById(R.id.lightInfoView);
         motionView = (TextView) findViewById(R.id.motionInfoView);
         motionView2 = (TextView) findViewById(R.id.motionInfoView2);
         soundView = (TextView) findViewById(R.id.soundInfoView);
         soundView2 = (TextView) findViewById(R.id.soundInfoView2);

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
                if(soundValue >250){
                    soundView.setVisibility(View.GONE);
                    soundView2.setVisibility(View.VISIBLE);
                    soundView2.setText("Sound Levels High");
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(750); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    soundView2.startAnimation(anim);
                }else if((soundValue < 250)){
                    soundView2.setVisibility(View.GONE);
                    soundView.setVisibility(View.VISIBLE);
                    soundView.setText("Sound Levels Optimal");
                    soundView2.clearAnimation();
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
                    motionView2.setVisibility(View.GONE);
                    motionView.setVisibility(View.VISIBLE);
                    motionView.setText("No Movement Detected");
                    motionView2.clearAnimation();
                }else if((motionValue > 20)){
                    motionView.setVisibility(View.GONE);
                    motionView2.setVisibility(View.VISIBLE);
                    motionView2.setText("Movement Detected");
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(750); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    motionView2.startAnimation(anim);
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
                if(lightValue > 500) {
                    lightView.setText("Room is Bright:");
                }else if((lightValue < 400)){
                    lightView.setText("Room is Dark:");
                }else{
                    lightView.setText("Room getting Darker:");
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
                String airString = Integer.toString(airValue);
                if(airValue <= 400){
                    airTextView.setTextColor(Color.WHITE);
                    airTextView.setText("Air Quality is: Good");
                    airTextView.clearAnimation();;
                }
                else if(airValue > 400){
                    airTextView.setText("Air Quality is: Bad");
                    airTextView.setTextColor(Color.RED);
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(750); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    airTextView.startAnimation(anim);
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
                String humidString = Integer.toString(humidValue);
                htextView.setText("Weather");
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
                String tempString = Integer.toString(tempValue);
                tTextView.setText("Temp:" + " \n" +tempString+"c");
                Log.d("TAG", "Value is: " + tempValue);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


    }
    public void onClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   Weather.class);
        startActivity(myIntent);
    }

    public void tempOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   TempControl.class);
        startActivity(myIntent);
    }

    public void moveOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   VideoScreen.class);
        startActivity(myIntent);
    }

}
