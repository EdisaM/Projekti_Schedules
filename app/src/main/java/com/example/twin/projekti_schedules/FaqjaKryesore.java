package com.example.twin.projekti_schedules;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;


import java.util.ArrayList;
import java.util.List;

public class FaqjaKryesore extends AppCompatActivity{

    private List<Aktiviteti> activities;
    private Context context;
    private RecyclerView rv;
    private Menu menu;
    private Toolbar toolbar;
    private MenuInflater menuInflater;
    private SearchManager searchManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.faqjakryesore);






        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("BeProductive");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

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
        activities.add(new Aktiviteti("Add activity", R.mipmap.add));

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(activities);
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent intent;
        switch(item.getItemId()){
            case R.id.motivation:
                intent =  new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.menuAbout:
                intent =  new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.menuSettings:
                Toast.makeText(this, "You clicked settings", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuLogout:
                intent =  new Intent(this, MainActivity.class);
                startActivity(intent);


                break;

        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

}

