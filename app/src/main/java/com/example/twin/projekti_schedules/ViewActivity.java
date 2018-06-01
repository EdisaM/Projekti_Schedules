package com.example.twin.projekti_schedules;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import java.util.Date;
import java.util.Calendar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Button;

public class ViewActivity extends AppCompatActivity {


    static final ArrayList<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity_view);
        ListView list=(ListView)findViewById(android.R.id.list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FaqjaKryesore.class));
                finish();
            }
        });


        SimpleAdapter adapter = new SimpleAdapter(this,list1,R.layout.row, new String[] {
                "Activity type","Activity", "Time", "Date" }, new int[] {
                 R.id.txt,R.id.et1, R.id.time, R.id.date });

        addData();
        list.setAdapter(adapter);
    }

    public void addData() {
        HashMap<String, String> temp = new HashMap<String, String>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            temp.put("Activity type", getIntent().getExtras().getString("Activity type"));
        temp.put("Activity", getIntent().getExtras().getString("Activity"));
        temp.put("Time",getIntent().getExtras().getString("Time"));
        temp.put("Date", getIntent().getExtras().getString("Date"));


        list1.add(temp);
        }

       /* Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());*/
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