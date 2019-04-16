package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
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
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
//        mAuth = FirebaseAuth.getInstance();




//        myRefTemp.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String babyName  = dataSnapshot.getValue(String.class);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });


}

//    private void updateUI(FirebaseUser user) {
//
//        if (user != null) {
//            Toast.makeText(getBaseContext(), "Signed In" , Toast.LENGTH_SHORT ).show();
//        } else {
//            Toast.makeText(getBaseContext(), "Not signed in" , Toast.LENGTH_SHORT ).show();
//        }
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }



    public void onClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   VideoScreen.class);
        startActivity(myIntent);
    }

    public void cribOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   TempScreen.class);
        startActivity(myIntent);
    }

    public void messOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(),   MessageOptions.class);
        startActivity(myIntent);
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
