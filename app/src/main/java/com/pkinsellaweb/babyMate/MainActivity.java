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
    private int itemCount =0;

//    public MainActivity(){}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefStatus = database.getReference("Status");
        mMessage.clear();
        final ListAdapter myAdapter = new CustomAdapter(this,mMessage);


        DatabaseReference myRefSound = database.getReference("Sound");
        DatabaseReference myRefLight = database.getReference("Light");
        DatabaseReference myRefMovement = database.getReference("Movement");
//        DatabaseReference myRefName = database.getReference("Name");
//        DatabaseReference myRefBestTemp = database.getReference("BestTemp");
      DatabaseReference myRefWarning = database.getReference("Warning");








//        myRefMovement.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
//                Integer movementValue  = dataSnapshot.getValue(Integer.class);
//
//                ((CustomAdapter) myAdapter).notifyDataSetChanged();
//                if((movementValue) > 50){
//
//                    mMessage.add( "Movement Detected in ");
//                    itemCount++;
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w("TAG", "Failed to read value");
//            }
//        });
//
//
//
//
//
//        myRefTemp.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
//                Integer tempValue = dataSnapshot.getValue(Integer.class);
//                Integer bestTemp = 18;
//
//                if((tempValue) != bestTemp) {
//                    mMessage.add(" room Temp is: " + tempValue+"c" +  " \n" +mydate);
//                    itemCount++;
//                    ((CustomAdapter) myAdapter).notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG", "Failed to read value");
//            }
//        });
//
//        myRefLight.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
//                Integer lightValue = dataSnapshot.getValue(Integer.class);
//
//                if((lightValue) > 100){
//                    if(mMessage.contains(lightValue)){
//                        Log.d("TAG", "No Need" + lightValue);
//                    }else{
//                        mMessage.add("The Room is to Bright for " );
//                        itemCount++;
//                        mMessage.add("The Room Light Levels are to High: " + lightValue  + " \n" +mydate);
//                        itemCount++;
//                        ((CustomAdapter) myAdapter).notifyDataSetChanged();
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG", "Failed to read value");
//            }
//        });
//
//        myRefSound.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
//                Integer soundValue = dataSnapshot.getValue(Integer.class);
//
//                Log.d("TAG", "itemTest");
//                if((soundValue) >200){
//                    if(mMessage.contains(soundValue)){
//                        Log.d("TAG", "No Need" + soundValue);
//                    }else{
//                        mMessage.add("Sound Levels are High" );
//                        itemCount++;
//                        mMessage.add("The Room Sound Levels are: " + soundValue + " \n" +mydate);
//                        itemCount++;
//                    }
//                        Log.w("TAG", "test");
//                    mMessage.add("Sound Levels are High" );
//                    itemCount++;
//                    mMessage.add("The Room Sound Levels are: " + soundValue + " \n" +mydate);
//                    itemCount++;
//
//                    ((CustomAdapter) myAdapter).notifyDataSetChanged();
//
//                }
//
//
//                ((CustomAdapter) myAdapter).notifyDataSetChanged();
//                Log.w("TAG", "test2 ");
//                // Toast.makeText(getBaseContext(), "Number of warnings is " +itemCount, Toast.LENGTH_SHORT ).show();
//
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.w("TAG", "Failed to read value");
//            }
//
//
//        });


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
//        Integer itemCount = mMessage.size();
//        Log.d("TAG", "ListSize" + itemCount);
//        myRefWarning.setValue(itemCount);

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

        Log.d("TAG", "ListSize2" + itemCount);
        Intent intent = new Intent(MainActivity.this, MessageOptions.class);
//        intent.putExtra("key", itemCount);
        startActivity(intent);


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
