package com.example.gauravsingh.restdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private ArrayList<MovieDTO> mDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUi();
        fetchMovies();
    }

    private void setUpUi() {
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mDataset = new ArrayList<>();
        mAdapter = new RecyclerViewAdapter(this,mDataset);
        mLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void fetchMovies() {
        new MyTask(){
            @Override
            protected void onPostExecute(ArrayList<MovieDTO> movies) {
                super.onPostExecute(movies);
                if(movies != null && movies.size() > 0) {
                    mDataset.clear();
                    mDataset.addAll(movies);
                    mAdapter.notifyDataSetChanged();
                } else if (movies != null && movies.size() == 0) {
                    // show empty list message
                }
            }
        }.execute();
    }
}
