package com.pkinsellaweb.babyMate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageOptions extends AppCompatActivity {
        private TextView warningView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_options);
        warningView = (TextView) findViewById(R.id.humidView);




        Intent incomingIntent = getIntent();
        int incomingKey = incomingIntent.getIntExtra("key",0);
        Log.d("Tag", "IncomingKey " + incomingKey );
        warningView.setText("Warnings: " + incomingKey);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Warning");

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Integer warningValue = dataSnapshot.getValue(Integer.class);
//                String tempString = Integer.toString(warningValue);
//                warningView.setText("Warnings: " +tempString);
//                Log.d("TAG", "Value is: " + tempString);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("Tag", "Failed to read value.", error.toException());
//            }
//        });
    }

    public void onClickWarning(View v){
        Intent myIntent = new Intent(getBaseContext(),   Message.class);
        startActivity(myIntent);
    }

    public void onClickNote(View v){
        Intent myIntent = new Intent(getBaseContext(),   Notifications.class);
        startActivity(myIntent);
    }
}
