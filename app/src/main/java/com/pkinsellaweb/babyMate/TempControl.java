package com.pkinsellaweb.babyMate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TempControl extends AppCompatActivity {
    private TextView tempControl;
    private Button tempButtonUp;
    private Button tempButtonDown;
    private int mCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_control);
        Toast.makeText(getBaseContext(), "Not Connected - Check Connection" , Toast.LENGTH_SHORT ).show();
        tempControl =  (TextView) findViewById(R.id.tcontrolView);
        tempButtonUp = (Button) findViewById(R.id.upButton);
        tempButtonDown = (Button) findViewById(R.id.tempDown);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefTemp = database.getReference("Temp");
        myRefTemp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 mCounter = dataSnapshot.getValue(Integer.class);

                tempControl.setText(mCounter+"c");


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

        tempButtonUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCounter++;
               if(mCounter >= 24){
                   Toast.makeText(getBaseContext(), "Temperature at Highest" , Toast.LENGTH_SHORT ).show();
                   mCounter =24;
                }

                tempControl.setText(mCounter +"c");
            }
        });

        tempButtonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter--;
                if(mCounter <= 17){
                    Toast.makeText(getBaseContext(), "Temperature at Lowest" , Toast.LENGTH_SHORT ).show();
                    mCounter =17;
                }
                tempControl.setText(mCounter +"c");
            }
        });

    }

}
