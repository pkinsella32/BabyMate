package com.pkinsellaweb.babyMate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;

public class Message extends AppCompatActivity  {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;
    private String babyName;
    private Button button;








  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        button = findViewById(R.id.clearButton);
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String dayOfWeek = (new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
        Log.d("TAG", "Day " +dayOfWeek);

      FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference myRefTemp = database.getReference("Temp");
      DatabaseReference myRefHumid = database.getReference("Humid");
      DatabaseReference myRefSound = database.getReference("Sound");
      DatabaseReference myRefLight = database.getReference("Light");
      DatabaseReference myRefMovement = database.getReference("Movement");
      DatabaseReference myRefName = database.getReference("Name");
//        DatabaseReference myRefBestTemp = database.getReference("BestTemp");
      DatabaseReference myRefWarning = database.getReference("Warning");




        final ListAdapter myAdapter = new CustomAdapter(this,mMessage);
        ListView myListView = (ListView) findViewById(R.id.messageList);
        myListView.setAdapter(myAdapter);

      myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              Intent intent = new Intent(getApplicationContext(),VideoScreen.class);
              intent.putExtra("name",mMessage);
              startActivity(intent);
              FirebaseDatabase database = FirebaseDatabase.getInstance();
              DatabaseReference myRef = database.getReference("Warning");

              ((CustomAdapter) myAdapter).notifyDataSetChanged();



          }
      });




                myRefName.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        babyName = dataSnapshot.getValue(String.class);
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
                        Integer movementValue = dataSnapshot.getValue(Integer.class);

                        ((CustomAdapter) myAdapter).notifyDataSetChanged();
                        if ((movementValue) > 50) {

                            mMessage.add("Movement Detected in " + babyName + "room " + mydate);
                            // itemCount++;

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TAG", "Failed to read value");
                    }
                });


                myRefTemp.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                        Integer tempValue = dataSnapshot.getValue(Integer.class);
                        Integer bestTemp = 18;

                        if ((tempValue) != bestTemp) {
                            mMessage.add(babyName + "s" + " room Temp is: " + tempValue + "c" + " \n" + mydate);
                            //itemCount++;
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

                        if ((lightValue) > 100) {
                            if (mMessage.contains(lightValue)) {
                                Log.d("TAG", "No Need" + lightValue);
                            } else {
                                mMessage.add("The Room is to Bright for ");
                                //  itemCount++;
                                mMessage.add("The Room Light Levels are to High: " + lightValue + " \n" + mydate);
                                // itemCount++;
                                ((CustomAdapter) myAdapter).notifyDataSetChanged();
                            }

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
                        Log.d("TAG", "Day ");
                        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                        Integer soundValue = dataSnapshot.getValue(Integer.class);

                        Log.d("TAG", "sound value1 " +soundValue);
                         if ((soundValue) > 250) {
                            if (mMessage.contains(soundValue)) {
                                Log.d("TAG", "No Need" + soundValue);
                            } else {
                                mMessage.add("Sound Levels are High");
                                // itemCount++;
                                mMessage.add("The Room Sound Levels are: " + soundValue + " \n" + mydate);
                                // itemCount++;
                            }
                            Log.w("TAG", "test");
                            mMessage.add("Sound Levels are High");
                            // itemCount++;
                            mMessage.add("The Room Sound Levels are: " + soundValue + " \n" + mydate);
                            //itemCount++;


                        }

                        ((CustomAdapter) myAdapter).notifyDataSetChanged();


                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("TAG", "Failed to read value");
                    }

                });




      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
             int itemCount = mMessage.size();

              FirebaseDatabase database = FirebaseDatabase.getInstance();
              DatabaseReference myRefWarning = database.getReference("Warning");
              myRefWarning.setValue(itemCount);
              mMessage.clear();
              mMessage.add("No Warnings Left!");
              ((CustomAdapter) myAdapter).notifyDataSetChanged();

          }
      });


  }


}
