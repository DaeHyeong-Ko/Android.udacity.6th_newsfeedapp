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
    private static final String dateAndtime_SEPARATOR = "T";

    public newsfeedappAdapter(Activity context, ArrayList<newsfeedapp> newsfeedapps) {
        super(context, 0, newsfeedapps);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main, parent, false);
        }
        newsfeedapp newsList = getItem(position);

        TextView title = (TextView) listitemView.findViewById(R.id.article_title);
        title.setText(newsList.getTitle());

        String originalDate = newsList.getDate();
        String primaryDate = "";
        String dateOffset = "";

        if (originalDate.contains(dateAndtime_SEPARATOR)) {
            String[] parts = originalDate.split(dateAndtime_SEPARATOR);
            dateOffset = parts[0];
            primaryDate = dateAndtime_SEPARATOR + parts[1];
        }

        TextView dateobject = (TextView) listitemView.findViewById(R.id.article_date1);
        TextView timeobject = (TextView) listitemView.findViewById(R.id.article_date2);

        dateobject.setText(dateOffset);
        timeobject.setText(primaryDate);

        TextView authorobject = (TextView) listitemView.findViewById(R.id.author_name);
        TextView sectionobject = (TextView) listitemView.findViewById(R.id.section_name);

        authorobject.setText(newsList.getAuthorName());
        sectionobject.setText(newsList.getSectionName());

        return listitemView;
    }
}
