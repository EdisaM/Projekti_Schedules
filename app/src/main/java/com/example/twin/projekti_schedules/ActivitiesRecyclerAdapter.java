package com.example.twin.projekti_schedules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Edisa on 6/9/2018.
 */

public class ActivitiesRecyclerAdapter extends RecyclerView.Adapter<ActivitiesRecyclerAdapter.ActivityViewHolder>{

    private ArrayList<AddActivity_values> listAddActivityvalues;
    private ArrayList<AddActivity_values> listAddActivityvalues1;

    private Context mContext;
    private ArrayList<AddActivity_values> mFilteredList;
    SqliteHelper databaseHelper;


    public ActivitiesRecyclerAdapter(ArrayList<AddActivity_values> listAddActivityvalues, Context mContext) {
        this.listAddActivityvalues = listAddActivityvalues;
        this.listAddActivityvalues1 = listAddActivityvalues;
        this.mContext = mContext;
        this.mFilteredList = listAddActivityvalues;
        this.notifyDataSetChanged();


    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewActivity;
        public AppCompatTextView textViewType;
        public AppCompatTextView textViewDate;
        public AppCompatTextView textViewTime;
        public TextView status;
        public LinearLayout l1;
        public LinearLayout l2;
        public LinearLayout l3;
        public CardView cardView;
        public CheckBox checkStatus;
        public Button btnUpdate;



        public ActivityViewHolder(View view) {
            super(view);
            textViewActivity = (AppCompatTextView) view.findViewById(R.id.textViewActivity);
            textViewType = (AppCompatTextView) view.findViewById(R.id.textViewType);
            textViewDate = (AppCompatTextView) view.findViewById(R.id.textViewDate);
            textViewTime = (AppCompatTextView) view.findViewById(R.id.textViewTime);
            checkStatus=(CheckBox)view.findViewById(R.id.checkStatus);
            cardView=(CardView)view.findViewById(R.id.cardV);
            l1=(LinearLayout)view.findViewById(R.id.l1);
            l2=(LinearLayout)view.findViewById(R.id.l2);
            l3=(LinearLayout)view.findViewById(R.id.l3);
            btnUpdate=(Button)view.findViewById(R.id.btnUpdate);






        }


    }




    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addactivity_view_row, parent, false);

        return new ActivityViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ActivityViewHolder holder, final int position) {


       if(position%2==0) {
           // holder.cardView.setBackgroundColor(Color.parseColor("#fff176"));
           holder.l1.setBackgroundColor(Color.parseColor("#AFE7C271"));
        }
        else{
           // holder.cardView.setBackgroundColor(Color.parseColor("#dce775"));
           holder.l1.setBackgroundColor(Color.parseColor("#AF75BF91"));
        }


        holder.textViewActivity.setText(listAddActivityvalues.get(position).getActivity());
        holder.textViewType.setText(listAddActivityvalues.get(position).getActivityType());
        holder.textViewDate.setText(listAddActivityvalues.get(position).getDate());
        holder.textViewTime.setText(listAddActivityvalues.get(position).getTime());







        final String activityType=holder.textViewType.getText().toString();
        final String activity1=holder.textViewActivity.getText().toString();
        final String date1=holder.textViewDate.getText().toString();
        final String time1=holder.textViewTime.getText().toString();
        holder.checkStatus.setTag(position);
        holder.checkStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                SqliteHelper db=new SqliteHelper(compoundButton.getContext());
                db.updateActivities("1", activity1, activityType, date1, time1);
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),CompletedActivity.class);
                intent.putExtra("Activity", listAddActivityvalues.get(position).getActivity());
                intent.putExtra("Type", listAddActivityvalues.get(position).getActivityType());
                intent.putExtra("Date", listAddActivityvalues.get(position).getDate());
                intent.putExtra("Time", listAddActivityvalues.get(position).getTime());
                view.getContext().startActivity(intent);
            }
        });




    }




    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    }

