package com.pkinsellaweb.babyMate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    private EditText editText;
    private Button nameButton;
    private TextView textView;
    private EditText tempEdititext;
    private Button tempButton;
    private TextView temptextView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRefName = database.getReference("Name");
    DatabaseReference myRefBestTemp = database.getReference("BestTemp");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        editText = (EditText) findViewById(R.id.nameTxt);
        nameButton = (Button) findViewById(R.id.nameButton);
        textView = (TextView) findViewById(R.id.nameView);
        tempEdititext = (EditText) findViewById(R.id.tempTxt);
        tempButton = (Button) findViewById(R.id.setTempButton);
        temptextView = (TextView) findViewById(R.id.tempView);

        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRefBestTemp = database.getReference("BestTemp");
                myRefBestTemp.setValue(tempEdititext.getText().toString());
                Toast.makeText(getBaseContext(), "Temperature has been saved" , Toast.LENGTH_SHORT ).show();
                tempEdititext.setText("");
            }
        });

        myRefBestTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String roomtemp  = dataSnapshot.getValue(String.class);
                temptextView.setText(roomtemp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value");
            }
        });

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRefName = database.getReference("Name");
                myRefName.setValue(editText.getText().toString());
                Toast.makeText(getBaseContext(), "Name has been saved" , Toast.LENGTH_SHORT ).show();
                editText.setText("");
            }
        });

        myRefName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String babyName  = dataSnapshot.getValue(String.class);
                textView.setText(babyName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("TAG", "Failed to read value");
            }
        });
    }






}
