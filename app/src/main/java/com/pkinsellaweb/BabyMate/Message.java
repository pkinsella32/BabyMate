package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


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

      myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent = new Intent(getApplicationContext(),VideoScreen.class);
              intent.putExtra("name",mMessage);
              startActivity(intent);


          }
      });

//      final ListAdapter myAdapter2 = new CustomAdapter2(this,mMessage);
//      ListView myListView2 = (ListView) findViewById(R.id.messageList2);
//      myListView2.setAdapter(myAdapter2);

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




        myRefMovement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                Integer movementValue  = dataSnapshot.getValue(Integer.class);

                ((CustomAdapter) myAdapter).notifyDataSetChanged();
                if((movementValue) > 20){

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
              Integer humidValue = dataSnapshot.getValue(Integer.class);
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
              Integer bestTemp = 20;

              if((tempValue) != bestTemp) {
                  mMessage.add(babyName+"s" +" room is not comfortable," +"\n" +"Take Action!");
                  mMessage.add(babyName+"s" +" room Temp is: " + tempValue+"c" +  " \n" +mydate);
                  ((CustomAdapter) myAdapter).notifyDataSetChanged();
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
              Integer lightValue = dataSnapshot.getValue(Integer.class);

            if((lightValue) > 10){
                 mMessage.add("The Room is to Bright for " + babyName + " To sleep");
                 mMessage.add("The Room Light Levels are: " + lightValue  + " \n" +mydate);
                 ((CustomAdapter) myAdapter).notifyDataSetChanged();
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
              Integer soundValue = dataSnapshot.getValue(Integer.class);
              if((soundValue) >30){
                  mMessage.add(babyName + " is crying");
                  mMessage.add("The Room Sound Levels are: " + soundValue + " \n" +mydate);
                  ((CustomAdapter) myAdapter).notifyDataSetChanged();
              }
          }

          @Override
          public void onCancelled(DatabaseError databaseError) {
              Log.w("TAG", "Failed to read value");
          }
      });
    }



}
