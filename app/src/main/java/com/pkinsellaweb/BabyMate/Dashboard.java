package com.pkinsellaweb.BabyMate;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        barChart = (BarChart) findViewById(R.id.barChart1);
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(18f,0));
        barEntries.add(new BarEntry(38f,1));
        barEntries.add(new BarEntry(303f,2));
        barEntries.add(new BarEntry(74f,3));
        barEntries.add(new BarEntry(11f,4));
        barEntries.add(new BarEntry(91f,5));
        BarDataSet barDataSet = new BarDataSet(barEntries,"BabyMate System Data");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("Temp");
        theDates.add("Humid");
        theDates.add("Light");
        theDates.add("Sound");
        theDates.add("Movement");
        theDates.add("Co2");

        BarData theData = new BarData(theDates,barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);


    }


}
