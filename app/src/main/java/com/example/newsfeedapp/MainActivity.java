package com.example.newsfeedapp;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.app.LoaderManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<newsfeedapp>>{

    private static final String guardianapi ="https://content.guardianapis.com/search?show-tags=contributor&show-fields=thumbnail&api-key=d24ca8ad-39d3-4387-9e33-0887f76956d0";
    private newsfeedappAdapter mAdapter;
    private static final int newsapp_LOADER_ID = 1;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        mAdapter = new newsfeedappAdapter(this, new ArrayList<newsfeedapp>());
        ListView listview = (ListView) findViewById(R.id.list_item);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listview.setEmptyView(mEmptyStateTextView);

        ConnectivityManager CnMng = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NIF = CnMng.getActiveNetworkInfo();

        if(NIF != null && NIF.isConnected()){
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(newsapp_LOADER_ID, null, this);
        }
        else{
            View loadingIndicator = findViewById(R.id.progress_circular);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }


        listview.setAdapter(mAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                newsfeedapp currentnewsapp = mAdapter.getItem(position);
                Uri newsappUri = Uri.parse(currentnewsapp.getUrl());
                Intent website = new Intent(Intent.ACTION_VIEW, newsappUri);
                startActivity(website);
            }
        });
    }

    @Override
    public Loader<List<newsfeedapp>> onCreateLoader(int i, Bundle bundle){
        return new newsfeedappLoader(this, guardianapi);
    }

    @Override
    public void onLoadFinished(Loader<List<newsfeedapp>> loader, List<newsfeedapp> newsfeedapps){
        View progressbar = findViewById(R.id.progress_circular);
        progressbar.setVisibility(View.GONE);
        mAdapter.clear();
        mEmptyStateTextView.setText(R.string.no_news);
        if(newsfeedapps !=null && !newsfeedapps.isEmpty()){
            mAdapter.addAll(newsfeedapps);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<newsfeedapp>> loader)
    {
        mAdapter.clear();
    }

}