package com.example.twin.projekti_schedules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Edisa on 6/9/2018.
 */

public class ActivitiesRecyclerAdapter extends RecyclerView.Adapter<ActivitiesRecyclerAdapter.ActivityViewHolder> {

    private ArrayList<AddActivity_values> listAddActivityvalues;
    private Context mContext;
    private ArrayList<AddActivity_values> mFilteredList;
    SqliteHelper databaseHelper;


    public ActivitiesRecyclerAdapter(ArrayList<AddActivity_values> listAddActivityvalues, Context mContext) {
        this.listAddActivityvalues = listAddActivityvalues;
        this.mContext = mContext;
        this.mFilteredList = listAddActivityvalues;


    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewActivity;
        public AppCompatTextView textViewType;
        public AppCompatTextView textViewDate;
        public AppCompatTextView textViewTime;
        public CheckBox checkStatus;


        public ActivityViewHolder(View view) {
            super(view);
            textViewActivity = (AppCompatTextView) view.findViewById(R.id.textViewActivity);
            textViewType = (AppCompatTextView) view.findViewById(R.id.textViewType);
            textViewDate = (AppCompatTextView) view.findViewById(R.id.textViewDate);
            textViewTime = (AppCompatTextView) view.findViewById(R.id.textViewTime);
            checkStatus=(CheckBox)view.findViewById(R.id.checkStatus);


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

        holder.textViewActivity.setText(listAddActivityvalues.get(position).getActivity());
        holder.textViewType.setText(listAddActivityvalues.get(position).getActivityType());
        holder.textViewDate.setText(listAddActivityvalues.get(position).getDate());
        holder.textViewTime.setText(listAddActivityvalues.get(position).getTime());
        holder.checkStatus.setChecked(listAddActivityvalues.get(position).isSelected());

        holder.checkStatus.setTag(position);
        holder.checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Integer position= (Integer)holder.checkStatus.getTag();


                if (listAddActivityvalues.get(position).isSelected()) {
                    listAddActivityvalues.get(position).setSelected(false);
                    listAddActivityvalues.get(position).setStatus("0");
                } else {

                    listAddActivityvalues.get(position).setSelected(true);






                }
            }
        });




    }




    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }


    }

