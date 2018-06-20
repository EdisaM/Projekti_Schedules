package com.example.twin.projekti_schedules;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class MenuActivity extends FaqjaKryesore {

    public static boolean isFragmentLoaded;
    public static Fragment menuFragment;
    TextView title;
    public static ImageView menuButton;
    public static FragmentTransaction fragmentTransaction;
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAddlayout(R.layout.menu_activity);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();


         isFragmentLoaded=false;
        Intent intent = getIntent();
         text = intent.getStringExtra("intent");






       /* Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences setting = getSharedPreferences("Prefs", 0);
        int lastDay = setting.getInt("day", 0);
        if (lastDay != currentDay) {
            SharedPreferences.Editor editor = setting.edit();
            editor.putInt("day", currentDay);
            editor.commit();*/

















        title = (TextView) findViewById(R.id.title_top);
        menuButton = (ImageView) findViewById(R.id.menu_icon);
        menuButton.setImageResource(R.drawable.menuico);
        title.setText("BeProductive");
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFragmentLoaded) {
                    loadFragment();

                }
                else {
                    if (isFragmentLoaded) {
                      //  if (menuFragment.isAdded()) {
                         hideFragment();

                      //  }
                    }
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();  // Always call the superclass method first
        final LocalData localData = new LocalData(getApplicationContext());
        localData.set_hour(12);
        localData.set_min(30);

        NotificationScheduler.setReminder(MenuActivity.this,AlarmReceiver.class,

                localData.get_hour(),localData.get_min());
    }

    public void hideFragment(){
       fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
        fragmentTransaction.remove(menuFragment);
        fragmentTransaction.commit();
        menuButton.setImageResource(R.drawable.menuico);
        isFragmentLoaded = false;

    }
    public void loadFragment(){
        FragmentManager fm = getSupportFragmentManager();
        menuFragment = fm.findFragmentById(R.id.container);
        menuButton.setImageResource(R.drawable.menuico);

        if(menuFragment == null){
            menuFragment = new MenuFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_down, R.anim.slide_up);
            fragmentTransaction.add(R.id.container,menuFragment);
            fragmentTransaction.commit();
        }


        isFragmentLoaded = true;
    }
    public static String getString(){
        return text;
    }
}