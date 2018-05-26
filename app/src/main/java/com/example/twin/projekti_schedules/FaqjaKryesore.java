package com.example.twin.projekti_schedules;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;


import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

public class FaqjaKryesore extends Activity{

    private List<Aktiviteti> activities;
    private Context context;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.faqjakryesore);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        activities = new ArrayList<>();
        activities.add(new Aktiviteti("Today's schedule", R.mipmap.schedule));
        activities.add(new Aktiviteti("Health", R.mipmap.health));
        activities.add(new Aktiviteti("Education", R.mipmap.education));
        activities.add(new Aktiviteti("Entertainment", R.mipmap.entertainment));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(activities,context);
        rv.setAdapter(adapter);
    }
}

