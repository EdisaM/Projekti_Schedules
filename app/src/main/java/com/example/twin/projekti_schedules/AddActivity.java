package com.example.twin.projekti_schedules;

import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.view.MenuInflater;
import android.widget.Toast;
import android.app.AlarmManager;

import java.util.Calendar;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.Button;
import java.util.ArrayList;
import android.widget.CheckBox;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity_1 = AddActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutActivity;
    private TextInputLayout textInputLayoutDate;
    private TextInputLayout textInputLayoutTime;

    private TextInputEditText date;
    private TextInputEditText activity;
    private TextInputEditText time;
    //public String s;


    public PendingIntent pendingIntent;
    public String text;

    private AppCompatButton buttonAdd;
    private AppCompatTextView textViewList;

    TextView ActivityType;
    CheckBox checkalarm;
    AlarmManager alarmmanager;

    DatePickerDialog datePickerDialog;
    Button button;
    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;
    private AddActivity_values addActivityvalues;
    String activityType;




    private static final String TAG = "AddActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);
        initViews();
        initObjects();
        initListeners();
       // s=LoginActivity.id;

        final LocalData localData = new LocalData(getApplicationContext());




        ClipboardManager myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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




        Intent alarmIntent = new Intent(AddActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(AddActivity.this, 0, alarmIntent, 0);

        ArrayList<ItemData> lista=new ArrayList<>();
        lista.add(new ItemData("Select activity type:",R.mipmap.add));
        lista.add(new ItemData("Health",R.mipmap.health));
        lista.add(new ItemData("Education",R.mipmap.education));
        lista.add(new ItemData("Entertainment",R.mipmap.entertainment));
        lista.add(new ItemData("Work",R.mipmap.success));

        Spinner sp=(Spinner)findViewById(R.id.spinner);
        SpinnerAdapter adapter=new SpinnerAdapter(this,
                R.layout.spinner_layout,R.id.txt,lista);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch(position)
                {
                    case 1:
                    activityType="Health";
                            break;
                    case 2:
                        activityType="Education";
                        break;
                    case 3:
                        activityType="Entertainment";
                        break;
                    case 4:
                        activityType="Work";
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                    public void onTimeSet(TimePicker timePicker, final int selectedHour,final int selectedMinute) {
                        localData.set_hour(selectedHour);
                        localData.set_min(selectedMinute);

                        time.setText(selectedHour + ":" + selectedMinute);
                        //NotificationScheduler.setRemindertime(AddActivity.this,AlarmReceiver.class,

                              // localData.get_hour(),localData.get_min());



                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        int selectedHour=localData.get_hour();
        int selectedMinute=localData.get_min();
       // NotificationScheduler.setReminder(AddActivity.this,AlarmReceiver.class,

             //   localData.get_hour(),localData.get_min());



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
                            public void onDateSet(DatePicker view,final int year, final int monthOfYear,final int dayOfMonth) {



                                // set day of month , month and year value in the edit text
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                               // NotificationScheduler.setReminderdate(AddActivity.this,AlarmReceiver.class,

                                    //    localData.get_day(),localData.get_month(),localData.get_year());



                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });

        final int selectedDay= localData.get_day();
        final int selectedMonth=localData.get_month();
        final int selectedYear=localData.get_year();
    }

    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutActivity = (TextInputLayout) findViewById(R.id.textInputLayoutActivity);
        textInputLayoutDate = (TextInputLayout) findViewById(R.id.textInputLayoutDate);
        textInputLayoutTime = (TextInputLayout) findViewById(R.id.textInputLayoutTime);


        activity = (TextInputEditText) findViewById(R.id.activity);
        date = (TextInputEditText) findViewById(R.id.date);
        time = (TextInputEditText) findViewById(R.id.time_);

        buttonAdd = (AppCompatButton) findViewById(R.id.btnadd);

        textViewList = (AppCompatTextView) findViewById(R.id.appCompatTextViewBenefList);

    }


    private void initListeners() {
        buttonAdd.setOnClickListener(this);
        textViewList.setOnClickListener(this);

    }

    private void initObjects() {
        sqliteHelper = new SqliteHelper(this);
        addActivityvalues = new AddActivity_values();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnadd:



                postDataToSQLite();
                break;
        }
    }
    private void postDataToSQLite() {
            text =LoginActivity.id;

            addActivityvalues.setActivityType(activityType.trim());
            addActivityvalues.setActivity(activity.getText().toString().trim());
            addActivityvalues.setDate(date.getText().toString().trim());
            addActivityvalues.setTime(time.getText().toString().trim());
            addActivityvalues.setStatus("0");
            addActivityvalues.setID(text);


            sqliteHelper.addActivities(addActivityvalues);
            // Snack Bar to show success message that record saved successfully
            Intent intent_view = new Intent(this, ViewActivity.class);
            intent_view.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Toast.makeText(this, "Activity added successfully!", Toast.LENGTH_SHORT)
                    .show();


            startActivity(intent_view);
            finish();


        }

   /* public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

         //Set the alarm to start at 10:30 AM
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 37);

        //Repeating on every 20 minutes interval
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);
    }  */





    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}


