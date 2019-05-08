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
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private ImageView online;
    private ImageView offline;
    private TextView dayOfWeek;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefStatus = database.getReference("Status");

        dayOfWeek = (TextView) findViewById(R.id.day);
        online = (ImageView) findViewById(R.id.online);
        offline = (ImageView) findViewById(R.id.offline);

        MonthDay m = MonthDay.now();
        String n = m.format(DateTimeFormatter.ofPattern("d"));
        Integer result = Integer.valueOf(n);
        String newN = n+"th";
         if(result ==2){
            newN = n+"nd";
        }else if(result == 3){
            newN = n+"rd";
        }else if(result >= 4){
             newN = n+"th";
        }else if(result ==1||result ==31){
              newN = n+"st";
         }



        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String dow = (new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
        dayOfWeek.setText(dow + " " +newN);




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
        Intent intent = new Intent(MainActivity.this, MessageOptions.class);
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
