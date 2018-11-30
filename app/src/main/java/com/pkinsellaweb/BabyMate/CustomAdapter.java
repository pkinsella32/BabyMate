package com.pkinsellaweb.BabyMate;
import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<String> {

     public CustomAdapter( Context context, ArrayList<String> mMessage) {
         super(context, R.layout.custom_list, mMessage);
     }

     @NonNull
     @Override
     public View getView(int position, View convertView,  ViewGroup parent) {
         LayoutInflater myInflater = LayoutInflater.from(getContext());
         View customView = myInflater.inflate(R.layout.custom_list,parent,false);

         String singleMessageItem = getItem(position);
         TextView text1 = (TextView) customView.findViewById(R.id.text1);
         ImageView warningImage = (ImageView) customView.findViewById(R.id.warningImage);

         text1.setText(singleMessageItem);
         warningImage.setImageResource(R.drawable.warning);
         return customView;

     };
 }
