package com.example.alexisferret.moodtracker;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class HistoryActivity extends MainActivity {

    private ConstraintLayout backgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        backgroundColor = findViewById(R.id.background_color);

        final RecyclerView history = (RecyclerView) findViewById(R.id.history);
        history.setLayoutManager(new LinearLayoutManager(this));
        history.setAdapter(new MyAdapter());
    }
}
