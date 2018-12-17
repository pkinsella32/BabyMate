package com.pkinsellaweb.BabyMate;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Dashboard extends AppCompatActivity {

    BarChart barChart;
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);





        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Integer barTemp = dataSnapshot.child("Temp").getValue(Integer.class);
                final Integer barHumid = dataSnapshot.child("Humid").getValue(Integer.class);
                final Integer barLight = dataSnapshot.child("Light").getValue(Integer.class);
                final Integer barSound = dataSnapshot.child("Sound").getValue(Integer.class);
                final Integer barMovement = dataSnapshot.child("Temp").getValue(Integer.class);

                barChart = (BarChart) findViewById(R.id.barChart1);
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(barTemp,0));
                barEntries.add(new BarEntry(barHumid,1));
                barEntries.add(new BarEntry(barLight,2));
                barEntries.add(new BarEntry(barSound,3));
                barEntries.add(new BarEntry(barMovement,4));
                BarDataSet barDataSet = new BarDataSet(barEntries,"BabyMate System Data");

                ArrayList<String> theDates = new ArrayList<>();
                theDates.add("Temp");
                theDates.add("Humid");
                theDates.add("Light");
                theDates.add("Sound");
                theDates.add("Movement");


                BarData theData = new BarData(theDates,barDataSet);
                barChart.setData(theData);

                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setScaleEnabled(true);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }


}
