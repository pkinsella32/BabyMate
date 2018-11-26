package com.pkinsellaweb.BabyMate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class AI extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText itemEt;
    private Button btn;
    //private Button aiButton;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        itemEt = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list);
        //  aiButton = findViewById(R.id.ai_button);






        items = FileHelper.readData( this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);
        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_btn:
                String itemEntered = itemEt.getText().toString();
                adapter.add(itemEntered);
                itemEt.setText("Enter Another Module ");

                FileHelper.writeData(items, this);
                Toast.makeText(this, "Module Added", Toast.LENGTH_SHORT).show();

                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
