package com.example.twin.projekti_schedules;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emredavarci.circleprogressbar.CircleProgressBar;

import java.util.Calendar;

/**
 * Created by Edisa on 6/18/2018.
 */

public class StatisticsSliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    public static String text;

    public final Calendar c = Calendar.getInstance();
    public final int mYear = c.get(Calendar.YEAR); // current year
    public final int mMonth = c.get(Calendar.MONTH); // current month
    public final int Month=mMonth+1;
    public final int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
    String monthYear="/"+Month+"/"+ mYear; //current month
    String date = mDay + "/" + Month + "/" + mYear;
    public StatisticsSliderAdapter(Context context){this.context=context;};

    public String[] slide_headings={
            "TOTAL PROGRESS",
            "TODAY'S PROGRESS",
            "THIS MONTH'S PROGRESS"
    };



    @Override
    public int getCount() {
        return slide_headings.length;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.statistics_slide_layout, container, false);
        TextView slideHeading=(TextView)view.findViewById(R.id.statistics_slide_heading);
        CircleProgressBar progressBar = (CircleProgressBar)view.findViewById(R.id.progressBar);

        Integer completedActivities;
        Integer incompletedActivities;
        Integer allActivities;
        double completed;
        int d;
        text=LoginActivity.id;

        TextView txt1=(TextView)view.findViewById(R.id.heading1);
        TextView txt2=(TextView)view.findViewById(R.id.heading2);
        TextView txt3=(TextView)view.findViewById(R.id.heading3);
        slideHeading.setText(slide_headings[position]);
        SqliteHelper db = new SqliteHelper(view.getContext());
        switch (position){
            case 0:
                completedActivities=db.CountActivities("1",text);
                incompletedActivities=db.CountActivities("0",text);
                allActivities=completedActivities+incompletedActivities;
                completed=((double)completedActivities/allActivities)*100;
                d=(int)completed;
                progressBar.setProgress(d);
                progressBar.setText(String.valueOf(d));
                txt1.setText("You have completed a total of "+completedActivities+" activities out of a total of "+allActivities+ "!");
                txt2.setText("You have a completion rate of "+d+"%");
                txt3.setText("You have a total of " + incompletedActivities+" left to complete!");
                break;
            case 1:
                completedActivities=db.CountActivities(date,"1",text);
                incompletedActivities=db.CountActivities(date,"0",text);
                allActivities=completedActivities+incompletedActivities;
                completed=((double)completedActivities/allActivities)*100;
                d=(int)completed;
                progressBar.setProgress(d);
                progressBar.setText(String.valueOf(d));
                txt1.setText("You have completed "+completedActivities+" activities out of "+allActivities+ " today!");
                txt2.setText("You have a completion rate of "+d+"%");
                txt3.setText("You have " + incompletedActivities+" left to complete today!");
                break;
            case 2:
                completedActivities=db.CountActivitiesByMonth(monthYear,"1",text);
                incompletedActivities=db.CountActivitiesByMonth(monthYear,"0",text);
                allActivities=completedActivities+incompletedActivities;
                completed=((double)completedActivities/allActivities)*100;
                d=(int)completed;
                progressBar.setProgress(d);
                progressBar.setText(String.valueOf(d));
                txt1.setText("You have completed "+completedActivities+" activities out of "+allActivities+ " this month!");
                txt2.setText("You have a completion rate of "+d+"%");
                txt3.setText("You have " + incompletedActivities+" left to complete this month!");
        }

        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
