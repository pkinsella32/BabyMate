package com.pkinsellaweb.BabyMate;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Message extends AppCompatActivity  {
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;
    private String babyName;
    private Button button;



    public Message(){}

  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        button = findViewById(R.id.clearButton);




        final ListAdapter myAdapter = new CustomAdapter(this,mMessage);
        ListView myListView = (ListView) findViewById(R.id.messageList);
        myListView.setAdapter(myAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMessage.clear();
                ((CustomAdapter) myAdapter).notifyDataSetChanged();

            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefHumid = database.getReference("Humid");
        DatabaseReference myRefSound = database.getReference("Sound");
        DatabaseReference myRefLight = database.getReference("Light");
        DatabaseReference myRefMovement = database.getReference("Movement");
        DatabaseReference myRefName = database.getReference("Name");
        DatabaseReference myRefBestTemp = database.getReference("BestTemp");

        myRefName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                babyName  = dataSnapshot.getValue(String.class);
                Log.w("TAG", babyName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value");
            }
        });

//        myRefSound.setValue("50");
       // myRefLight.setValue("50");
//        myRefMovement.setValue("50");


        myRefMovement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                String movementValue  = dataSnapshot.getValue(String.class);

                ((CustomAdapter) myAdapter).notifyDataSetChanged();
                if(Integer.parseInt(movementValue) > 20){
                    mMessage.add( babyName +  " is Moving:  " +  " \n" +mydate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value");
            }
        });

      myRefHumid.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
              String humidValue = dataSnapshot.getValue(String.class);
              mMessage.add(babyName+"s" + " Room Humidity is: " + humidValue  + " \n" +mydate);
              ((CustomAdapter) myAdapter).notifyDataSetChanged();
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });

      myRefTemp.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
              Integer tempValue = dataSnapshot.getValue(Integer.class);
              mMessage.add(babyName+"s" +" room Temp is: " + tempValue+"c" +  " \n" +mydate);
              ((CustomAdapter) myAdapter).notifyDataSetChanged();
              if((tempValue) > 20){
                  mMessage.add(babyName+"s" +" room is to Warm," +"\n" +"Take Action!");
              } else if((tempValue) < 20){
                  mMessage.add(babyName+"s" +" room is to Cold," +"\n" +"Take Action!");
              }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });

      myRefLight.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());




             String lightValue = dataSnapshot.getValue(String.class);
             mMessage.add("The Room Light Levels are: " + lightValue  + " \n" +mydate);
             ((CustomAdapter) myAdapter).notifyDataSetChanged();

             if(Integer.parseInt(lightValue) > 40){
                 mMessage.add("The Room is to Bright for " + babyName + " To sleep");
             }

          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });

      myRefSound.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
              String soundValue = dataSnapshot.getValue(String.class);
              mMessage.add("The Room Sound Levels are: " + soundValue + " \n" +mydate);
              ((CustomAdapter) myAdapter).notifyDataSetChanged();

              if(Integer.parseInt(soundValue) >50){
                  mMessage.add(babyName + " is crying");
              }else{
                  mMessage.add(babyName + " is sleeping");
              }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });
    }



}
