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

public class MMD extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText mmdItemEt;
    private Button mmdBtn;
   // private Button aiButton;
    private ListView mmdItemsList;

    private ArrayList<String> mmdItems;
    private ArrayAdapter<String> mmdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mmd);

        mmdItemEt = findViewById(R.id.mmd_item_edit_text);
        mmdBtn = findViewById(R.id.mmd_add_btn);
        mmdItemsList = findViewById(R.id.mmd_items_list);
        //  aiButton = findViewById(R.id.ai_button);




        mmdItems = MmdFileHelper.readData( this);

        mmdAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mmdItems);
        mmdItemsList.setAdapter(mmdAdapter);

        mmdBtn.setOnClickListener(this);
        mmdItemsList.setOnItemClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mmd_add_btn:
                String itemEntered = mmdItemEt.getText().toString();
                mmdAdapter.add(itemEntered);
                mmdItemEt.setText("Enter Another Module ");

                MmdFileHelper.writeData(mmdItems, this);
                Toast.makeText(this, "Module Added", Toast.LENGTH_SHORT).show();

                break;
        }
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        mmdItems.remove(position);
        mmdAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
}

