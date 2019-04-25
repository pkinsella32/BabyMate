package com.pkinsellaweb.babyMate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewDash extends AppCompatActivity {
        private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dash);

        myList = (ListView) findViewById(R.id.sensorList);

        ArrayList<String> newList = new ArrayList<>();
        newList.add("Temperature Data");
        newList.add("Light Data");
        newList.add("Sound Data");
        newList.add("Movement Data");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, newList);
        myList.setAdapter(arrayAdapter);
    }
}
