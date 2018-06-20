package com.example.twin.projekti_schedules;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

import static java.security.AccessController.getContext;

public class ChangeDateTime {
    public String etDate1;
    public String etTime1;
    public String tvDate1;
    public String tvTime1;
    public String tvName1;



    public void showDialog(final Activity activity, final String name, final String msg, final String msg2){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popupdatetime);



        final TextView tvDate = (TextView) dialog.findViewById(R.id.tvDate);
        final TextView tvTime=(TextView) dialog.findViewById(R.id.tvTime);
        final TextView tvName=(TextView)dialog.findViewById(R.id.tvName);
        tvDate.setText(msg);
        tvTime.setText(msg2);
        tvName.setText(name);
        final EditText etDate=(EditText)dialog.findViewById(R.id.etDate);
        final EditText etTime=(EditText)dialog.findViewById(R.id.etTime);





        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                final TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(activity.getApplicationContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        etTime.setText(selectedHour + ":" + selectedMinute);

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });



        Button dialogButton = (Button) dialog.findViewById(R.id.btnchng);
        Button dialog1Button = (Button) dialog.findViewById(R.id.buttoncancel);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDate1 = etDate.getText().toString().trim();
                etTime1 = etTime.getText().toString().trim();
                tvDate1=tvDate.getText().toString().trim();
                tvTime1=tvTime.getText().toString().trim();
                tvName1=tvName.getText().toString().trim();
                SqliteHelper db=new SqliteHelper(activity.getApplicationContext());
                db.update_time(etTime1, etDate1, msg, msg2, name);
                //db.update_time(tvTime.toString(),etTime1);
                dialog.dismiss();


            }
        });

        dialog.show();
        dialog1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }});

    }
}