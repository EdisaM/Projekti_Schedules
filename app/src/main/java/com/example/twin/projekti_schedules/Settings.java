package com.example.twin.projekti_schedules;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Settings extends AppCompatActivity {



    private Menu menu;
    private Toolbar toolbar;
    private MenuInflater menuInflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.settings);


        //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);


        TextView txt1 = (TextView) findViewById(R.id.changepwd);
        TextView txt2 = (TextView) findViewById(R.id.delacc);


        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, ChangePassword.class);
                startActivity(intent);
            }


        });

        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Settings.this, DeleteAccount.class);
                startActivity(intent);
            }


        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final Intent intent;
        switch(item.getItemId()){
            case R.id.motivation:
                intent =  new Intent(this, motivation.class);
                startActivity(intent);
                break;
            case R.id.menuAbout:
                intent =  new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.menuSettings:
                intent =  new Intent(this, Settings.class);
                startActivity(intent);
                break;


            case R.id.menuLogout:

                intent =  new Intent(this, LoginActivity.class);
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