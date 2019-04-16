package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

public class weekSum extends AppCompatActivity {
        BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_sum);

        barchart = (BarChart) findViewById(R.id.barChart2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Integer barTemp = dataSnapshot.child("Temp").getValue(Integer.class);
                barchart = (BarChart) findViewById(R.id.barChart2);
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(18,0));
                barEntries.add(new BarEntry(22,1));
                barEntries.add(new BarEntry(17,2));
                barEntries.add(new BarEntry(24,3));
                barEntries.add(new BarEntry(17,4));
                BarDataSet barDataSet = new BarDataSet(barEntries,"BabyMate Temperature Data");

                ArrayList<String> theDates = new ArrayList<>();
                theDates.add("Monday");
                theDates.add("Tuesday");
                theDates.add("Wednesday");
                theDates.add("Thursday");
                theDates.add("Friday");


                BarData theData = new BarData(theDates,barDataSet);
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
