package com.pkinsellaweb.babyMate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Temp extends AppCompatActivity {

    private ArrayList<String> mTemp = new ArrayList<>();
    private ListView mListView;
    private TextView htextView;
    private TextView tTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);




        mListView = (ListView) findViewById(R.id.tempList);
       // htextView = (TextView) findViewById(R.id.humidView);
       // tTextView = (TextView) findViewById(R.id.tempScreenView);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mTemp);
        mListView.setAdapter(arrayAdapter);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefHumid = database.getReference("Humid");
        DatabaseReference myRefAirStatus = database.getReference("AirStatus");


        // Read from the database

        myRefHumid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer humidValue = dataSnapshot.getValue(Integer.class);
                mTemp.add("Room Humidity is: " + humidValue);
                arrayAdapter.notifyDataSetChanged();
                //htextView.setText(humidValue);

                Log.d("TAG", "Value is: " + humidValue);
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
                mTemp.add("Room Temperature is: " +tempValue+"c");
               // tTextView.setText(tempValue);
                arrayAdapter.notifyDataSetChanged();
                Log.d("TAG", "Value is: " + tempValue);

                if((tempValue) < 15){
                    mTemp.add("The room is currently very cold " +tempValue+"c"+ " Consider turning up the heat.");
                }
                else if((tempValue) < 18){
                    mTemp.add("The room is currently chilly " +tempValue+"c");
                }

                else if((tempValue) > 22){
                    mTemp.add("The room is currently to warm " +tempValue+"c"+" Turn on the fan!");
                }
                else{
                    mTemp.add("The room is at a comfortable temperature");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


    }
}
