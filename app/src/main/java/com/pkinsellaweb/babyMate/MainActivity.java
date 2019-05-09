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

    private ImageView online;
    private ImageView offline;
    private TextView dayOfWeek;
    private TextView tStatus;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefStatus = database.getReference("Status");

        dayOfWeek = (TextView) findViewById(R.id.day);
        online = (ImageView) findViewById(R.id.online);
        offline = (ImageView) findViewById(R.id.offline);
        tStatus = (TextView) findViewById(R.id.testStatus);

        MonthDay m = MonthDay.now();
        String n = m.format(DateTimeFormatter.ofPattern("d"));
        String n2 = m.format(DateTimeFormatter.ofPattern("M"));
        Integer result2 = Integer.valueOf(n2);
        String newN2 ="";
        if(result2 ==1){
            newN2 ="Jan";
        }else if(result2 ==2){
            newN2 = "Feb";
        }else if(result2 ==3){
            newN2 = "Mar";
        }else if(result2 ==4){
            newN2 = "Apr";
        }else if(result2 ==5){
            newN2 = "May";
        }else if(result2 ==6){
            newN2 = "Jun";
        }else if(result2 ==7){
            newN2 = "Jul";
        }else if(result2 ==8){
            newN2 = "Aug";
        }else if(result2 ==9){
            newN2 = "Sep";
        }else if(result2 ==10){
            newN2 = "Oct";
        }else if(result2 ==11){
            newN2 = "Nov";
        }else{
            newN2 = "Dec";
        }


        Integer result = Integer.valueOf(n);
        String newN = n+"th";
         if(result ==2 || result ==22){
            newN = n+"nd";
        }else if(result == 3){
            newN = n+"rd";
        }else if(result >= 4){
             newN = n+"th";
        }else if(result ==1||result ==31 || result ==21){
              newN = n+"st";
         }



        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String dow = (new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
        dayOfWeek.setText(dow + "-" +newN+"-"+newN2);




        myRefStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer statusValue = dataSnapshot.getValue(Integer.class);
                String statusString = Integer.toString(statusValue);
                if(statusValue ==0){
                    tStatus.setText("BabyMate: Offline!");
                    online.setVisibility(View.GONE);
                    offline.setVisibility(View.VISIBLE);
                    Log.w("TAG", "status" + statusValue);
                }else{
                    tStatus.setText("BabyMate: Online!");
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
