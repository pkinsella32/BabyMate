package com.pkinsellaweb.babyMate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private ImageView online;
    private ImageView offline;

//    public MainActivity(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefStatus = database.getReference("Status");

        final ListAdapter myAdapter = new CustomAdapter(this,mMessage);

        DatabaseReference myRefHumid = database.getReference("Humid");
        DatabaseReference myRefSound = database.getReference("Sound");
        DatabaseReference myRefLight = database.getReference("Light");
        DatabaseReference myRefMovement = database.getReference("Movement");
        DatabaseReference myRefName = database.getReference("Name");
        DatabaseReference myRefBestTemp = database.getReference("BestTemp");
        DatabaseReference myRefWarning = database.getReference("Warning");








        myRefMovement.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
                Integer movementValue  = dataSnapshot.getValue(Integer.class);

                ((CustomAdapter) myAdapter).notifyDataSetChanged();
                if((movementValue) > 50){

                    mMessage.add( "Movement Detected in ");

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
                mMessage.add( " Room Humidity is: " + humidValue  + " \n" +mydate);
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
                Integer bestTemp = 18;

                if((tempValue) != bestTemp) {
                    mMessage.add(" room Temp is: " + tempValue+"c" +  " \n" +mydate);
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

                if((lightValue) > 100){
                    mMessage.add("The Room is to Bright for " );
                    mMessage.add("The Room Light Levels are to High: " + lightValue  + " \n" +mydate);
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

                Log.d("TAG", "itemTest");
                if((soundValue) >300){
                    mMessage.add("Sound Levels are High in " );
                    mMessage.add("The Room Sound Levels are: " + soundValue + " \n" +mydate);

                    ((CustomAdapter) myAdapter).notifyDataSetChanged();

                }


                ((CustomAdapter) myAdapter).notifyDataSetChanged();
                Log.w("TAG", "test2 ");
                // Toast.makeText(getBaseContext(), "Number of warnings is " +itemCount, Toast.LENGTH_SHORT ).show();

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value");
            }


        });


        online = (ImageView) findViewById(R.id.online);
        offline = (ImageView) findViewById(R.id.offline);

        myRefStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer statusValue = dataSnapshot.getValue(Integer.class);
                String statusString = Integer.toString(statusValue);
                if(statusValue ==0){
                    online.setVisibility(View.GONE);
                    offline.setVisibility(View.VISIBLE);
                    Log.w("TAG", "status" + statusValue);
                }else{
                    online.setVisibility(View.VISIBLE);
                    offline.setVisibility(View.GONE);
                    Log.w("TAG", "status" + statusValue);

                }
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

    public void cribOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   TempScreen.class);
        startActivity(myIntent);
    }

    public void messOnClick(View v){
        Integer itemCount = mMessage.size();
        Intent intent = new Intent(MainActivity.this, MessageOptions.class);
        intent.putExtra("key", itemCount);
        startActivity(intent);
        mMessage.clear();

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
