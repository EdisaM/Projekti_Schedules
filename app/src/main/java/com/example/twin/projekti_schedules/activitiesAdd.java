package com.example.twin.projekti_schedules;

/**
 * Created by Edisa on 5/31/2018.
 */

public class activitiesAdd {
        public String id;
        public String activityType;
        public String activity;
        public String date;
        public String time;
        public String status;

        public activitiesAdd(String id, String activityType, String activity, String date, String time, String status) {
            this.id = id;
            this.activityType = activityType;
            this.activity = activity;
            this.date = date;
            this.time = time;
            this.status = status;
        }
}
