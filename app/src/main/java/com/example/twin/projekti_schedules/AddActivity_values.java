package com.example.twin.projekti_schedules;


public class AddActivity_values {

    public AddActivity_values(){

    }


    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String activityType;
    public String activity;
    public String date;
    public String time;
    public String status;
    public String id;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSelected;

    public AddActivity_values(String activityType, String activity, String date, String time, String status,String id) {

        this.activityType = activityType;
        this.activity = activity;
        this.date = date;
        this.time = time;
        this.status = status;
        this.id=id;
    }
}

