package com.example.twin.projekti_schedules;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jaison on 17/06/17.
 */

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub

        if (intent.getAction() != null && context != null) {


                // For our recurring task, we'll just display a message
                Toast.makeText(context, "I'm running", Toast.LENGTH_SHORT).show();
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min(),localData.get_day(),localData.get_month(),localData.get_year());
                //NotificationScheduler.setReminderdate(context, AlarmReceiver.class, localData.get_day(), localData.get_month(),localData.get_year());

                return;
            }
        }

        Log.d(TAG, "onReceive: ");

        //Trigger the notification
        NotificationScheduler.showNotification(context, AddActivity.class,
                "You have to finish a task.", "Have a look at your schedule!");

    }
}