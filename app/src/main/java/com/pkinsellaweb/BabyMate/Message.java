package com.pkinsellaweb.BabyMate;

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

import java.util.ArrayList;

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

        myRefSound.setValue("225");
        myRefLight.setValue("450");

      myRefHumid.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(DataSnapshot dataSnapshot) {
              String humidValue = dataSnapshot.getValue(String.class);
              mMessage.add("Room Humidity is:" + humidValue);
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
              String tempValue = dataSnapshot.getValue(String.class);
              mMessage.add("Room Temperature is:" + tempValue+"c");
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
              String lightValue = dataSnapshot.getValue(String.class);
              mMessage.add("The Room Light Levels are:" + lightValue);
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
              String soundValue = dataSnapshot.getValue(String.class);
              mMessage.add("The Room Sound Levels are:" + soundValue);
              arrayAdapter.notifyDataSetChanged();
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });
    }
}
