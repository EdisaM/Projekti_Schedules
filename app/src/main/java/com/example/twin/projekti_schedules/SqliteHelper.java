package com.example.twin.projekti_schedules;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Edisa on 5/24/2018.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "register1.db";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;

    //TABLE NAME USERS
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN email
    public static final String KEY_EMAIL = "email";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";
    //here ends user table data
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





    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";
    public static final String SQL_TABLE_ACTIVITY = " CREATE TABLE " + TABLE_ACTIVITY
            + " ( "
            + KEY_ID_ACTIVITY + " INTEGER PRIMARY KEY, "
            + KEY_ACTIVITY_TYPE + " TEXT, "
            + KEY_ACTIVITY + " TEXT, "
            + KEY_DATE + " TEXT, "
            + KEY_TIME + " TEXT, "
            + KEY_STATUS + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);
        sqLiteDatabase.execSQL(SQL_TABLE_ACTIVITY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
    }


    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);

        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public void addActivity(activitiesAdd A){
        //get writable database
        SQLiteDatabase database = this.getWritableDatabase();

        //create content values to insert
        ContentValues value = new ContentValues();

        //Put activity type in  @values
        value.put(KEY_ACTIVITY_TYPE, A.activityType);

        //Put activity in  @values
        value.put(KEY_ACTIVITY, A.activity);

        //Put date in  @values
        value.put(KEY_DATE, A.date);
        //put time in @values
        value.put(KEY_TIME, A.time);
        //put status in @values
        value.put(KEY_STATUS, A.status);

        // insert row
        long id = database.insert(TABLE_ACTIVITY, null, value);

    }
    public Cursor getActivities(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/M/yyyy");
        String formattedDate = df.format(c);
        SQLiteDatabase db=getReadableDatabase();
        return db.query(TABLE_ACTIVITY,  new String[] { "activityType, activity, date, time" }, KEY_DATE + "=" + "'"+formattedDate+"'", null, null, null, null);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
}
