package com.pkinsellaweb.babyMate;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TempData extends AppCompatActivity {
    private int mon = 18;
    private int tue = 22;
    private int wed = 22;
    private int thu = 17;
    private int fri = 18;
    private int sat = 23;
    private int sund = 22;

    TextView tempView;
    BarChart barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_data);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        final String dow = (new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));

        barchart = (BarChart) findViewById(R.id.barChart3);
        tempView = (TextView) findViewById(R.id.tempView2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Integer barTemp = dataSnapshot.child("Temp").getValue(Integer.class);
                barchart = (BarChart) findViewById(R.id.barChart3);
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                if(dow.equals("Monday")){
                    barEntries.add(new BarEntry(barTemp,0));
                    int average = barTemp+tue+wed+thu+fri+sat+sund;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(mon,0));
                }

                if(dow.equals("Tuesday")){
                    barEntries.add(new BarEntry(barTemp,1));
                    int average = mon+barTemp+wed+thu+fri+sat+sund;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(tue,1));
                }

                if(dow.equals("Wednesday")){
                    barEntries.add(new BarEntry(barTemp,2));
                    int average = mon+tue+barTemp+thu+fri+sat+sund;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(wed,2));
                }

                if(dow.equals("Thursday")){
                    barEntries.add(new BarEntry(barTemp,3));
                    int average = mon+tue+wed+barTemp+fri+sat+sund;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(thu,3));
                }

                if(dow.equals("Friday")){
                    barEntries.add(new BarEntry(barTemp,4));
                    int average = mon+tue+wed+thu+barTemp+sat+sund;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(fri,4));
                }

                if(dow.equals("Saturday")){
                    barEntries.add(new BarEntry(barTemp,5));
                    int average = mon+tue+wed+thu+fri+barTemp+sund;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(sat,5));
                }

                if(dow.equals("Sunday")){
                    barEntries.add(new BarEntry(barTemp,6));
                    int average = mon+tue+wed+thu+fri+sat+barTemp;
                    int newAve = average/5;
                    tempView.setText("Avg:"+newAve);
                }else{
                    barEntries.add(new BarEntry(sund,6));
                }
//                barEntries.add(new BarEntry(mon,0));
//                barEntries.add(new BarEntry(tue,1));
//                barEntries.add(new BarEntry(wed,2));
//                barEntries.add(new BarEntry(thu,3));
//                barEntries.add(new BarEntry(fri,4));
//                barEntries.add(new BarEntry(sat,5));
//                barEntries.add(new BarEntry(sund,6));
                BarDataSet barDataSet = new BarDataSet(barEntries,"BabyMate Temp Data");

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
