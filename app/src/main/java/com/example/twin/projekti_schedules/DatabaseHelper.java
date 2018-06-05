package com.example.twin.projekti_schedules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;

/**
 * Created by Edisa on 6/1/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "activities.db";
    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;


    //here begins activity data
    public static final String TABLE_ACTIVITY="activities";
    //activity type
    public static final String KEY_ID_ACTIVITY="idActivity";
    public static final String KEY_ACTIVITY_TYPE="activityType";

    //activity
    public static final String KEY_ACTIVITY="activity";

    //date
    public static final String KEY_DATE="date";

    //time
    public static final String KEY_TIME="time";

    //status
    public static final String KEY_STATUS="status";

    public static final String SQL_TABLE_ACTIVITY = " CREATE TABLE " + TABLE_ACTIVITY
            + " ( "
            + KEY_ID_ACTIVITY + " INTEGER PRIMARY KEY, "
            + KEY_ACTIVITY_TYPE + " TEXT, "
            + KEY_ACTIVITY + " TEXT, "
            + KEY_DATE + " TEXT, "
            + KEY_TIME + " TEXT, "
            + KEY_STATUS + " TEXT"
            + " ) ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_ACTIVITY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
