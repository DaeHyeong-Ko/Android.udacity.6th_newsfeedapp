package com.example.newsfeedapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class newsfeedappAdapter extends ArrayAdapter<newsfeedapp> {
    private static final String dateandtime_SEPARATOR = "T";

    public newsfeedappAdapter(Activity context, ArrayList<newsfeedapp> newsfeedapps){
        super(context, 0, newsfeedapps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listitem = convertView;
        if(listitem == null){
            listitem = LayoutInflater.from(getContext()).inflate(R.layout.activity_main,parent,false);
        }
        newsfeedapp newslist = getItem(position);

        TextView title = (TextView) listitem.findViewById(R.id.article_title);
        title.setText(newslist.getTitle());

        String originaldate = newslist.getdate();
        String primarydate = "";
        String dateoffset = "";

        if(originaldate.contains(dateandtime_SEPARATOR)){
            String[] parts = originaldate.split(dateandtime_SEPARATOR);
            dateoffset = parts[0];
            primarydate = dateandtime_SEPARATOR + parts[1];
        }

        TextView dateobject = (TextView) listitem.findViewById(R.id.article_date1);
        TextView timeobject = (TextView) listitem.findViewById(R.id.article_date2);

        dateobject.setText(dateoffset);
        timeobject.setText(primarydate);

        return listitem;
    }
}
