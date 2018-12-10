package com.pkinsellaweb.BabyMate;

import android.support.v4.widget.SwipeRefreshLayout;
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

public class Temp extends AppCompatActivity {

    private ArrayList<String> mTemp = new ArrayList<>();
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);




        mListView = (ListView) findViewById(R.id.tempList);


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mTemp);
        mListView.setAdapter(arrayAdapter);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        DatabaseReference myRefHumid = database.getReference("Humid");

       myRefTemp.setValue("20");
      myRefHumid.setValue("27");
        // Read from the database

        myRefHumid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value1 = dataSnapshot.getValue(String.class);
                mTemp.add("Room Humidity is: " + value1);
                arrayAdapter.notifyDataSetChanged();
                Log.d("TAG", "Value is: " + value1);
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
                String value2 = dataSnapshot.getValue(String.class);
                mTemp.add("Room Temperature is: " +value2+"c");
                arrayAdapter.notifyDataSetChanged();
                Log.d("TAG", "Value is: " + value2);

                if(Integer.parseInt(value2) < 15){
                    mTemp.add("The room is currently very cold " +value2+"c"+ " Consider turning up the heat.");
                }
                else if(Integer.parseInt(value2) < 18){
                    mTemp.add("The room is currently chilly " +value2+"c");
                }

                else if(Integer.parseInt(value2) > 22){
                    mTemp.add("The room is currently to warm " +value2+"c"+" Turn on the fan!");
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
