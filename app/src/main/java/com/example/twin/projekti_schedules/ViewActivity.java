package com.example.twin.projekti_schedules;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import java.util.Date;
import java.util.Calendar;
import android.widget.SimpleAdapter;

public class ViewActivity extends ListActivity {


    static final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity_view);

        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.row, new String[] {
                "Activity type","Activity", "Time", "Date" }, new int[] {
                 R.id.txt,R.id.et1, R.id.time, R.id.date });

        addData();
        setListAdapter(adapter);
    }

    public void addData() {
        HashMap<String, String> temp = new HashMap<String, String>();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            temp.put("Activity type", getIntent().getExtras().getString("Activity type"));
        temp.put("Activity", getIntent().getExtras().getString("Activity"));
        temp.put("Time",getIntent().getExtras().getString("Time"));
        temp.put("Date", getIntent().getExtras().getString("Date"));


        list.add(temp);
        }

       /* Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view, menu);
        return true;
    }

}