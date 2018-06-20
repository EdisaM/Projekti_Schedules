package com.example.twin.projekti_schedules;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ViewActivity extends AppCompatActivity {
    private AppCompatActivity activity = ViewActivity.this;
    public String activity1;
    public String activitytype;
    public String date;
    public String time;
    public String text;
    public String id;

    public final Calendar c = Calendar.getInstance();
    public final int mYear = c.get(Calendar.YEAR); // current year
    public final int mMonth = c.get(Calendar.MONTH); // current month
    public final int Month=mMonth+1;
    public final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day


    Context context = ViewActivity.this;
    private RecyclerView recyclerViewBeneficiary;
    private ArrayList<AddActivity_values> listAddActivityvalues;
    private ActivitiesRecyclerAdapter activitiesRecyclerAdapter;
    public SqliteHelper databaseHelper;
    private ArrayList<AddActivity_values> filteredList;
    SearchView searchView;
    Spinner sp;
    CheckBox checkStatus;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //text = MenuActivity.getString();
        //placing toolbar in place of actionbar
        text= LoginActivity.id;


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

       // Toast.makeText(ViewActivity.this, "There are no activities for today "+text, Toast.LENGTH_LONG).show();









        /////////DATE FORMATTING FOR SPINNER METHODS////////////

        checkStatus=(CheckBox)findViewById(R.id.checkStatus);


        //CALLING METHODS
        initViews();
        initObjects();
        /////SPINNER INVOCATION
        sp=(Spinner)findViewById(R.id.filteractivities);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.choices, android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position){
                    case 0:
                                String date = mDay + "/" + Month + "/" + mYear;
                                if (databaseHelper.CountActivities(date, "0",text) > 0) {
                                    listAddActivityvalues.clear();
                                    listAddActivityvalues.addAll(databaseHelper.getFilteredActivities(date, "0",text));

                                }
                                else {
                                    Toast.makeText(ViewActivity.this, "There are no activities for today "+date, Toast.LENGTH_LONG).show();
                                    listAddActivityvalues.clear();
                                }
                        activitiesRecyclerAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        listAddActivityvalues.clear();
                        listAddActivityvalues.addAll(databaseHelper.getAllActivities("0",text));
                        Toast.makeText(ViewActivity.this, "You are now seeing all activities", Toast.LENGTH_SHORT).show();
                        activitiesRecyclerAdapter.notifyDataSetChanged();
                        break;
                    case 2:
                        String monthYear="/"+Month+"/"+ mYear;
                        if(databaseHelper.CountActivitiesByMonth(monthYear, "0",text)>0) {

                            listAddActivityvalues.clear();
                            listAddActivityvalues.addAll(databaseHelper.getFilteredActivitiesByMonth(monthYear, "0",text));
                            Toast.makeText(ViewActivity.this, "All activities for this month. ", Toast.LENGTH_SHORT).show();
                            activitiesRecyclerAdapter.notifyDataSetChanged();
                        }
                        else
                        {
                            Toast.makeText(ViewActivity.this, "There are no activities for this month. ", Toast.LENGTH_LONG).show();
                            listAddActivityvalues.clear();
                            activitiesRecyclerAdapter.notifyDataSetChanged();
                        }
                        break;



                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ///////////SEARCH VIEW INITIALIZATION/////////////////
        searchView=(SearchView)findViewById(R.id.search);
        searchView.setQueryHint("Search by date");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.length()>0){

                if(databaseHelper.CountActivities(query.toString().trim(), "0")>0){
                    listAddActivityvalues.clear();
                    listAddActivityvalues.addAll(databaseHelper.getFilteredActivities(query.toString().trim(), "0",text));
                    Toast.makeText(ViewActivity.this, "You have "+databaseHelper.CountActivities(query.toString().trim(),"0")+" activities in "+query, Toast.LENGTH_LONG).show();


                }else{
                    Toast.makeText(ViewActivity.this, "There are no activities for  "+query+" ", Toast.LENGTH_LONG).show();
                }

            } else{
                    listAddActivityvalues.clear();
                    listAddActivityvalues.addAll(databaseHelper.getAllActivities("0",text));

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
    }


    private void initViews() {
        recyclerViewBeneficiary = (RecyclerView) findViewById(R.id.recyclerViewBeneficiary);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listAddActivityvalues = new ArrayList<>();
        activitiesRecyclerAdapter = new ActivitiesRecyclerAdapter(listAddActivityvalues, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewBeneficiary.setLayoutManager(mLayoutManager);
        recyclerViewBeneficiary.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBeneficiary.setHasFixedSize(true);
        recyclerViewBeneficiary.setAdapter(activitiesRecyclerAdapter);
        databaseHelper = new SqliteHelper(activity);

        getDataFromSQLite();

    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {

        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String date = mDay + "/" + Month + "/" + mYear;
                if (databaseHelper.CountActivities(date, "0",text) > 0) {
                    listAddActivityvalues.clear();
                    listAddActivityvalues.addAll(databaseHelper.getFilteredActivities(date, "0",text));

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                activitiesRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}