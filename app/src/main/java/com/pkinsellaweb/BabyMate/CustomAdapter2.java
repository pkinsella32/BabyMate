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

class CustomAdapter2 extends ArrayAdapter<String> {

    public CustomAdapter2( Context context, ArrayList<String> mMessage) {
        super(context, R.layout.custom_list2, mMessage);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_list2,parent,false);

        String singleMessageItem = getItem(position);
        TextView text2 = (TextView) customView.findViewById(R.id.text2);
        ImageView thumdsUpImage = (ImageView) customView.findViewById(R.id.bell);

        text2.setText(singleMessageItem);
        thumdsUpImage.setImageResource(R.drawable.bell);
        return customView;

    };
}
