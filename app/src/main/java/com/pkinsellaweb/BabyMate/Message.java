package com.pkinsellaweb.BabyMate;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Message extends AppCompatActivity {
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);



        messageListView = (ListView) findViewById(R.id.messageList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mMessage);
        messageListView.setAdapter(arrayAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefHumid = database.getReference("Humid");
        DatabaseReference myRefSound = database.getReference("Sound");
        DatabaseReference myRefLight = database.getReference("Light");
        DatabaseReference myRefMovement = database.getReference("Movement");

        myRefSound.setValue("225");
        myRefLight.setValue("450");
        myRefMovement.setValue("25");

        myRefMovement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                String movementValue  = dataSnapshot.getValue(String.class);
                arrayAdapter.notifyDataSetChanged();
                if(Integer.parseInt(movementValue) > 20){
                    mMessage.add("The Baby is Moving:  " + " - " + " \n" +mydate);
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
              mMessage.add("Room Humidity is: " + humidValue  + " - " + " \n" +mydate);
              arrayAdapter.notifyDataSetChanged();
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
              String tempValue = dataSnapshot.getValue(String.class);
              mMessage.add("Room Temp is: " + tempValue+"c" +  " - " + " \n" +mydate);
              arrayAdapter.notifyDataSetChanged();
              if(Integer.parseInt(tempValue) >= 20){
                  mMessage.add("The Room is to Warm");
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
              mMessage.add("The Room Light Levels are:" + lightValue  + " \n" + " - "+mydate);
              arrayAdapter.notifyDataSetChanged();

              if(Integer.parseInt(lightValue) > 200){
                  mMessage.add("The Light is Currently Turn On");
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
              mMessage.add("The Room Sound Levels are:" + soundValue +  " - "+mydate);
              arrayAdapter.notifyDataSetChanged();
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });
    }
}
