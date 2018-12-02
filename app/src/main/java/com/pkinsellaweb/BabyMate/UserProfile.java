package com.pkinsellaweb.BabyMate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    private EditText editText;
    private Button nameButton;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        editText = (EditText) findViewById(R.id.nameTxt);
        nameButton = (Button) findViewById(R.id.nameButton);

        nameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRefName = database.getReference("Name");

                myRefName.setValue(editText.getText().toString());

                Toast.makeText(getBaseContext(), "Name has been saved" , Toast.LENGTH_SHORT ).show();
                editText.setText("");
            }
        });
    }





//    public void enterName(String babyName){
//
//    }
//
//    public void buttonOnClick(View v){
//        Button button = (Button) v;
//        babyName = (EditText) findViewById(R.id.nameTxt);
//
//    }
}
