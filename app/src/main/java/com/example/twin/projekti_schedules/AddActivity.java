package com.example.twin.projekti_schedules;

import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.view.MenuInflater;
import android.widget.Toast;
import android.app.AlarmManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.support.v7.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    EditText time;
    EditText date;
    EditText et1;
    Spinner spinner;
    TextView txt;
    CheckBox checkalarm;
     AlarmManager alarmmanager;

    DatePickerDialog datePickerDialog;
    Button button;
    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;


    private static final String TAG = "AddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);
        sqliteHelper = new SqliteHelper(this);
        //  initiate the edit text
        et1 = (EditText) findViewById(R.id.et1);
        time = (EditText) findViewById(R.id.time);
        date=(EditText)findViewById(R.id.date);
        final LocalData localData = new LocalData(getApplicationContext());
        ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);







        button=(Button)findViewById(R.id.btnadd);
        txt=(TextView)findViewById(R.id.txt);
        spinner=(Spinner)findViewById(R.id.spinner);







        // perform click event on edit text
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                finish();
            }
        });



        // perform click event listener on edit text
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        localData.set_hour(selectedHour);
                        localData.set_min(selectedMinute);

                        time.setText(selectedHour + ":" + selectedMinute);

                        NotificationScheduler.setReminder(AddActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                        }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        int selectedHour=localData.get_hour();
        int selectedMinute=localData.get_min();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                localData.set_day(dayOfMonth);
                                localData.set_month(monthOfYear);
                                localData.set_year(year);

                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
        int selectedDay=localData.get_day();
        int selectedMonth=localData.get_month();
        int selectedYear=localData.get_year();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String Date = date.getText().toString();
                    String Activity = et1.getText().toString();
                    String Time = time.getText().toString();

                    sqliteHelper.addActivity(new activitiesAdd(null, "test", Activity, Date, Time, "0"));

                NotificationScheduler.setReminder(AddActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());

                //String dt;
                Date cal = (Date) Calendar.getInstance().getTime();
                //dt = cal.toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
                String date1 = sdf.format(new Date());
                String today=date1.toString();


                //DateFormat sdf1 = new SimpleDateFormat("dd/M/yyyy");
                //String dateInString = date.getText().toString();
                //Date date2 = sdf1.parse(dateInString);
                //if(cal==date2) {
                    Intent i = new Intent(AddActivity.this, ViewActivity.class);
                    startActivity(i);


                //}


            }
        });



        ArrayList<ItemData> list=new ArrayList<>();
        list.add(new ItemData("Select activity type:",R.mipmap.add));
        list.add(new ItemData("Health",R.mipmap.health));
        list.add(new ItemData("Education",R.mipmap.education));
        list.add(new ItemData("Entertainment",R.mipmap.entertainment));
        list.add(new ItemData("Work",R.mipmap.success));
        Spinner sp=(Spinner)findViewById(R.id.spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.spinner_layout,R.id.txt,list);
        sp.setAdapter(adapter);


    }



}


