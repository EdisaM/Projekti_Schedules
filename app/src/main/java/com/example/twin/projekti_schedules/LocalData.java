package com.example.twin.projekti_schedules;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jaison on 18/06/17.
 */

public class LocalData {

    private static final String APP_SHARED_PREFS = "RemindMePref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus="reminderStatus";
    private static final String hour="hour";
    private static final String min="min";
    private static final String day="day";
    private static final String month="month";
    private static final String year="year";

    public LocalData(Context context)
    {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    // Settings Page Set Reminder

    public boolean getReminderStatus()
    {
        return appSharedPrefs.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus(boolean status)
    {
        prefsEditor.putBoolean(reminderStatus, status);
        prefsEditor.commit();
    }

    // Settings Page Reminder Time (Hour)



    public void set_hour(int h)
    {
        prefsEditor.putInt(hour, h);
        prefsEditor.commit();
    }
    public int get_hour()
    {
        return appSharedPrefs.getInt(hour, 20);
    }

    // Settings Page Reminder Time (Minutes)

    public int get_min()
    {
        return appSharedPrefs.getInt(min, 60);
    }

    public void set_min(int m)
    {
        prefsEditor.putInt(min, m);
        prefsEditor.commit();
    }

    public int get_day()
    {
        return appSharedPrefs.getInt(day, 30);
    }

    public void set_day(int d)
    {
        prefsEditor.putInt(day,d);
        prefsEditor.commit();
    }


    public int get_month()
    {
        return appSharedPrefs.getInt(month, 12);
    }

    public void set_month(int mm)
    {
        prefsEditor.putInt(day, mm);
        prefsEditor.commit();
    }

    public int get_year()
    {
        return appSharedPrefs.getInt(year, 0);
    }

    public void set_year(int y)
    {
        prefsEditor.putInt(year, y);
        prefsEditor.commit();
    }


    public void reset()
    {
        prefsEditor.clear();
        prefsEditor.commit();

    }

}