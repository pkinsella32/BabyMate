package com.pkinsellaweb.babyMate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

public class Dashboard extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    private static final String FILE_NAME = "Sensor Data.txt";
    BarChart barChart;
    private ArrayList<String> mMessage = new ArrayList<>();
    private ListView messageListView;
    PieChart pieChart;
    Button button;
    private TextView myView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        myView = (TextView) findViewById(R.id.newText);

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

                Integer tempSave = dataSnapshot.child("Temp").getValue(Integer.class);
                Integer humidSave = dataSnapshot.child("Humid").getValue(Integer.class);
                Log.d("DebugTag","TempSave Value is " + Integer.toString(tempSave) + "saved in " +getFilesDir());
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    fos.write(tempSave.intValue());
                    fos.write(humidSave.intValue());
                   // Toast.makeText(getBaseContext(), "Temp Number is " +tempSave+ " Humid number is " +humidSave, Toast.LENGTH_SHORT ).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    {
                       if(fos != null){
                           try {
                               fos.close();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                    }
                }

                FileInputStream fis = null;

                try{
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String readTempSave;
                    while ((readTempSave = br.readLine()) != null){
                        sb.append(tempSave).append("\n");
                    }

                    myView.setText(sb);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if(fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


                barChart = findViewById(R.id.barChart1);
                button = findViewById(R.id.sumButton);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(),weekSum.class);
                        //intent.putExtra("name",mMessage);
                        startActivity(intent);

                    }
                });

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

//public void load(View v){
//    FileInputStream fis = null;
//
//    try{
//        fis = openFileInput(FILE_NAME);
//        InputStreamReader isr = new InputStreamReader(fis);
//        BufferedReader br = new BufferedReader(isr);
//        StringBuilder sb = new StringBuilder();
//        String tempSave;
//        while ((tempSave = br.readLine()) != null){
//            sb.append(tempSave).append("\n");
//        }
//
//        myView.setText(sb.toString());
//    } catch (FileNotFoundException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }finally{
//        if(fis != null){
//            try {
//                fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}






}
