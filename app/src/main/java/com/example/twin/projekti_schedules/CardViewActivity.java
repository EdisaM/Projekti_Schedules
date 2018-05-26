package com.example.twin.projekti_schedules;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

    public class CardViewActivity extends Activity {

        TextView name;

        ImageView photo;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.cardview_activity);
            name = (TextView)findViewById(R.id.name);

            photo = (ImageView)findViewById(R.id.photo);

            name.setText("Health");

            photo.setImageResource(R.drawable.health);
        }
    }

