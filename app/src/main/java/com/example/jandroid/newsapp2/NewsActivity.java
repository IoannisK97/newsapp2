package com.example.jandroid.newsapp2;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {
    private static String USGS_REQUEST_URL;
    private NewsAdapter rAdapter;
    private TextView mEmptyTextView;
    private ProgressBar mProgressBar;
    private RecyclerView recyclerView;
    private ArrayList<News> news= new ArrayList<>();

    private static final int NEWS_LOADER_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Bundle b = getIntent().getExtras();

        USGS_REQUEST_URL = b.getString("url");

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        mEmptyTextView = findViewById(R.id.text_empty_view);
        mProgressBar = findViewById(R.id.loading_spinner);


        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mEmptyTextView.setText("No internet connection");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewsAdapter(this,news));


    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        if (news==null){
            return;
        }
        mProgressBar.setVisibility(View.GONE);
        mEmptyTextView.setText("Empty");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NewsAdapter(this,news));


    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        rAdapter.clear();
    }



}

