package com.example.twin.projekti_schedules;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CompletedActivity extends AppCompatActivity  {
    public ImageView img;
    public TextView activity2;
    public TextView date;
    public TextView time;
    public Button btn1;
    public Button btn2;
    SqliteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        img=(ImageView)findViewById(R.id.imgset);
        activity2=(TextView)findViewById(R.id.txt1);
        date=(TextView)findViewById(R.id.txt2);
        time=(TextView)findViewById(R.id.txt3);
        btn1=(Button)findViewById(R.id.btnUpdate1);
        btn2=(Button)findViewById(R.id.btnUpdate2);


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewActivity.class));
                finish();
            }
        });


        Bundle extras = getIntent().getExtras();
            final String activity1 = extras.getString("Activity");
            final String activityType=extras.getString("Type");
            final String date1=extras.getString("Date");
            final String time1=extras.getString("Time");

        switch (activityType){
            case "Health":
                img.setImageResource(R.mipmap.health);
                break;
            case "Education":
                img.setImageResource(R.mipmap.education);
                break;
            case "Entertainment":
                img.setImageResource(R.mipmap.entertainment);
                break;
            case "Work":
                img.setImageResource(R.mipmap.success);
                break;


            }
            activity2.setText(activity1);
        date.setText(date1);
        time.setText(time1);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqliteHelper db=new SqliteHelper(CompletedActivity.this);
                db.delete_activity(activity1,date1);
                Toast.makeText(CompletedActivity.this,"Activity deleted!",Toast.LENGTH_SHORT);
                Intent i=new Intent(CompletedActivity.this,ViewActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);
                finish();


            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeDateTime alert = new ChangeDateTime();
                alert.showDialog(CompletedActivity.this,activity1, date1, time1);

            }
        });


        }
}
