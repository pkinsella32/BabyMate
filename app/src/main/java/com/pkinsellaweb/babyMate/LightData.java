package com.pkinsellaweb.babyMate;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LightData extends AppCompatActivity {
    private int mon = 200;
    private int tue = 198;
    private int wed = 556;
    private int thu = 482;
    private int fri = 200;
    private int sat = 150;
    private int sund = 600;

    TextView lightView;
    BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_data);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String dow = (new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));


        barchart = (BarChart) findViewById(R.id.barChart2);
        lightView = (TextView) findViewById(R.id.lightView2);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Integer barLight = dataSnapshot.child("Light").getValue(Integer.class);
                barchart = (BarChart) findViewById(R.id.barChart2);
                ArrayList<BarEntry> barEntries = new ArrayList<>();
               // int average = mon+tue+wed+fri+sat+sund;

                if(dow.equals("Monday")){
                    barEntries.add(new BarEntry(barLight,0));
                    int average = barLight+tue+wed+thu+fri+sat+sund;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(mon,0));
                }


                if(dow.equals("Tuesday")){
                    barEntries.add(new BarEntry(barLight,1));
                    int average = mon+barLight+wed+thu+fri+sat+sund;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(tue,1));
                }

                if(dow.equals("Wednesday")){
                    barEntries.add(new BarEntry(barLight,2));
                    int average = mon+tue+barLight+thu+fri+sat+sund;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(wed,2));
                }

                if(dow.equals("Thursday")){
                    barEntries.add(new BarEntry(barLight,3));
                    int average = mon+tue+wed+barLight+fri+sat+sund;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(thu,3));
                }

                if(dow.equals("Friday")){
                    barEntries.add(new BarEntry(barLight,4));
                    int average = mon+tue+wed+thu+barLight+sat+sund;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(fri,4));
                }

                if(dow.equals("Saturday")){
                    barEntries.add(new BarEntry(barLight,5));
                    int average = mon+tue+wed+thu+fri+barLight+sund;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(sat,5));
                }

                if(dow.equals("Sunday")){
                    barEntries.add(new BarEntry(barLight,6));
                    int average = mon+tue+wed+thu+fri+sat+barLight;
                    int newAve = average/5;
                    lightView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(sund,6));
                }

                BarDataSet barDataSet = new BarDataSet(barEntries,"BabyMate Light Data");

                ArrayList<String> theDays = new ArrayList<>();
                theDays.add("Mon");
                theDays.add("Tue");
                theDays.add("Wed");
                theDays.add("Thu");
                theDays.add("Fri");
                theDays.add("Sat");
                theDays.add("Sun");


                BarData theData = new BarData(theDays,barDataSet);
                barchart.setData(theData);

                barchart.setTouchEnabled(true);
                barchart.setDragEnabled(true);
                barchart.setScaleEnabled(true);

                }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });
    }
}
