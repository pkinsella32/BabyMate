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

public class Strat extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText stratItemEt;
    private Button stratBtn;
    private Button aiButton;
    private ListView stratItemsList;

    private ArrayList<String> stratItems;
    private ArrayAdapter<String> stratAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strat);

        stratItemEt = findViewById(R.id.strat_item_edit_text);
        stratBtn = findViewById(R.id.strat_add_btn);
        stratItemsList = findViewById(R.id.strat_items_list);
        //  aiButton = findViewById(R.id.ai_button);




        stratItems = StratFileHelper.readData( this);

        stratAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stratItems);
        stratItemsList.setAdapter(stratAdapter);

        stratBtn.setOnClickListener(this);
        stratItemsList.setOnItemClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.strat_add_btn:
                String itemEntered = stratItemEt.getText().toString();
                stratAdapter.add(itemEntered);
                stratItemEt.setText("Enter Another Module ");

                StratFileHelper.writeData(stratItems, this);
                Toast.makeText(this, "Module Added", Toast.LENGTH_SHORT).show();

                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        stratItems.remove(position);
        stratAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
