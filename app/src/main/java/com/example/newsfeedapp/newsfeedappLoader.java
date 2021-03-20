package com.example.newsfeedapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class newsfeedappLoader extends AsyncTaskLoader<List<newsfeedapp>> {

    private String murl;

    public newsfeedappLoader(Context context, String url) {
        super(context);
        murl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<newsfeedapp> loadInBackground() {
        if (murl == null) {
            return null;
        }

        List<newsfeedapp> newsfeedapps = QueryUtils.fetchNewsFeedApp(murl);
        return newsfeedapps;
    }
}