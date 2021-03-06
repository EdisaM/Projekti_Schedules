package com.example.twin.projekti_schedules;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Edisa on 5/24/2018.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    //DATABASE NAME
    public static final String DATABASE_NAME = "registerdatafinal.db";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 2;

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

    public static final String KEY_ID1="email";

    public static final String KEY_ID_CHECK="idCheck";
    public static final Integer KEY_CHECK=0;
    public static final String TABLE_CHECK="check";




    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT PRIMARY KEY, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";
    public static final String SQL_TABLE_ACTIVITY = " CREATE TABLE " + TABLE_ACTIVITY
            + " ( "
            + KEY_ID_ACTIVITY + " INTEGER PRIMARY KEY,"
            + KEY_ACTIVITY_TYPE + " TEXT, "
            + KEY_ACTIVITY + " TEXT, "
            + KEY_DATE + " TEXT, "
            + KEY_TIME + " TEXT, "
            + KEY_STATUS + " TEXT,"
            +KEY_ID1 + " TEXT,"
    +" FOREIGN KEY("+KEY_ID1+") REFERENCES TABLE_USERS("+KEY_EMAIL+")"
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

    public void update_user (String oldPassword , String newPassword )
    {
        this.getWritableDatabase().execSQL("UPDATE " +TABLE_USERS+" SET "+KEY_PASSWORD+"='"+newPassword+"' WHERE "+KEY_PASSWORD+"='"+oldPassword+"'" );
    }
    public void delete_user (String password,String email)
    {
        this.getWritableDatabase().execSQL("DELETE FROM " +TABLE_USERS+" WHERE "+KEY_PASSWORD+"='"+password+"' AND "+KEY_EMAIL+"='"+email+"'" );
    }

    public void delete_activity (String name,String date)
    {
        this.getWritableDatabase().execSQL("DELETE FROM " +TABLE_ACTIVITY+" WHERE "+KEY_DATE+"='"+date+"' AND "+KEY_ACTIVITY+"='"+name+"'" );
    }

    public boolean checkUser(String email){
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = KEY_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public void updatePassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, password);
        db.update(TABLE_USERS, values, KEY_EMAIL+" = ?",new String[] { email });
        db.close();
    }




    public boolean checkUser(String email, String password){
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = KEY_EMAIL + " = ?" + " AND " +KEY_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    ////////////////////////////////////////ALL METHODS FOR ACTIVITIY HANDLING//////////////////////////////////////////////////////////////////////////////////

    public void addActivities(AddActivity_values addActivityvalues) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ACTIVITY_TYPE, addActivityvalues.getActivityType());
        values.put(KEY_ACTIVITY, addActivityvalues.getActivity());
        values.put(KEY_DATE, addActivityvalues.getDate());
        values.put(KEY_TIME, addActivityvalues.getTime());
        values.put(KEY_STATUS, addActivityvalues.getStatus());
        values.put(KEY_ID1,addActivityvalues.getID());

        db.insert(TABLE_ACTIVITY, null, values);
        db.close();
    }

    public void updateActivities (String status, String activity, String type, String date, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, status);
        db.update(TABLE_ACTIVITY, values, KEY_ACTIVITY+" = ? AND "+ KEY_ACTIVITY_TYPE+" = ? AND "+KEY_DATE+" = ? AND "+KEY_TIME+" = ?",new String[] { activity, type, date, time });
        db.close();
    }
    public void deleteActivity (String name,String date,String time)
    {
        this.getWritableDatabase().execSQL("DELETE FROM "+TABLE_ACTIVITY+" WHERE "+KEY_TIME+"='"+time+"' AND "+KEY_DATE+"='"+date+"' AND "+KEY_ACTIVITY+"='"+name+"'" );
    }


    public String GetUserID(String emailId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("SELECT "+KEY_EMAIL+" FROM "+TABLE_USERS+" WHERE "+KEY_EMAIL+"='"+emailId+"'");
        return emailId;
    }





    public void update_time (String newTime, String newDate, String oldDate , String oldTime,  String name )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TIME, newTime);
        values.put(KEY_DATE, newDate);
        db.update(TABLE_ACTIVITY, values, KEY_ACTIVITY+" = ? AND "+KEY_DATE+" = ? AND "+KEY_TIME+" = ?",new String[] {name, oldDate, oldTime });
        db.close();
    }

    public void update_date (String oldDate , String newDate,String oldTime,String newTime,String name )
    {
        this.getWritableDatabase().execSQL("UPDATE " +TABLE_ACTIVITY+" SET "+KEY_TIME+"='"+newTime+"' AND "+KEY_DATE+"='"+newDate+"' WHERE "+KEY_TIME+"='"+oldTime+"' AND "+KEY_DATE+"='"+oldDate+"' AND "+KEY_ACTIVITY+"='"+name+"'" );
    }


    public int CountActivities(String query, String status,String id){
        String[] columns = {
                KEY_ACTIVITY_TYPE,
                KEY_ACTIVITY,
                KEY_DATE,
                KEY_TIME,
                KEY_STATUS,
                KEY_ID1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITY, //Table to query
                columns,    //columns to return
                KEY_DATE+" = ? AND "+KEY_STATUS+" =? AND "+KEY_ID1+" =?",
                new String[] {query, status,id},
                null,
                null,
                null);

        int count=cursor.getCount();
        return count;

    }

    public int CountActivities(String status,String id){
        String[] columns = {
                KEY_ACTIVITY_TYPE,
                KEY_ACTIVITY,
                KEY_DATE,
                KEY_TIME,
                KEY_STATUS,
                KEY_ID1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITY, //Table to query
                columns,    //columns to return
                KEY_STATUS+" =? AND "+KEY_ID1+" =?",
                new String[] {status,id},
                null,
                null,
                null);

        int count=cursor.getCount();
        return count;
    }


    public List<AddActivity_values> getAllActivities(String status,String id) {
        // array of columns to fetch
        String[] columns = {
                KEY_ACTIVITY_TYPE,
                KEY_ACTIVITY,
                KEY_DATE,
                KEY_TIME,
                KEY_STATUS,
                KEY_ID1

        };
        // sorting orders
        List<AddActivity_values> addActivityvaluesList = new ArrayList<AddActivity_values>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_ACTIVITY, //Table to query
                columns,    //columns to return
                KEY_STATUS+"= ? AND "+KEY_ID1+" =?",        //columns for the WHERE clause
                new String[] {status,id},        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
               KEY_DATE); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddActivity_values addActivityvalues = new AddActivity_values();
                addActivityvalues.setActivityType(cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY_TYPE)));
                addActivityvalues.setActivity(cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY)));
                addActivityvalues.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                addActivityvalues.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                addActivityvalues.setID(cursor.getString(cursor.getColumnIndex(KEY_ID1)));
                // Adding user record to list
                addActivityvaluesList.add(addActivityvalues);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return addActivityvaluesList;
    }

    public List<AddActivity_values> getFilteredActivities(String filter,String status,String id) {
        // array of columns to fetch
        String[] columns = {
                KEY_ACTIVITY_TYPE,
                KEY_ACTIVITY,
                KEY_DATE,
                KEY_TIME,
                KEY_STATUS,
                KEY_ID1
        };
        // sorting orders
        List<AddActivity_values> addActivityvaluesList = new ArrayList<AddActivity_values>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_ACTIVITY, //Table to query
                columns,    //columns to return
                KEY_DATE+" = ? AND "+KEY_STATUS+" = ? AND "+KEY_ID1+" =?",
                new String[] {filter, status,id},
                null,
                null,
                null);


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddActivity_values addActivityvalues = new AddActivity_values();
                addActivityvalues.setActivityType(cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY_TYPE)));
                addActivityvalues.setActivity(cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY)));
                addActivityvalues.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                addActivityvalues.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                addActivityvalues.setID(cursor.getString(cursor.getColumnIndex(KEY_ID1)));
                // Adding user record to list
                addActivityvaluesList.add(addActivityvalues);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return addActivityvaluesList;
    }

    public List<AddActivity_values> getFilteredActivitiesByMonth(String filter, String status ,String id) {
        // array of columns to fetch
        String[] columns = {
                KEY_ACTIVITY_TYPE,
                KEY_ACTIVITY,
                KEY_DATE,
                KEY_TIME,
                KEY_STATUS,
                KEY_ID1
        };
        // sorting orders
        List<AddActivity_values> addActivityvaluesList = new ArrayList<AddActivity_values>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_ACTIVITY, //Table to query
                columns,    //columns to return
                KEY_DATE+" LIKE ? AND "+ KEY_STATUS+" = ? AND "+ KEY_ID1+" = ?",
                new String[] {"%"+filter,status,id},
                null,
                null,
                null);


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddActivity_values addActivityvalues = new AddActivity_values();
                addActivityvalues.setActivityType(cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY_TYPE)));
                addActivityvalues.setActivity(cursor.getString(cursor.getColumnIndex(KEY_ACTIVITY)));
                addActivityvalues.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                addActivityvalues.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                addActivityvalues.setID(cursor.getString(cursor.getColumnIndex(KEY_ID1)));
                // Adding user record to list
                addActivityvaluesList.add(addActivityvalues);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return addActivityvaluesList;
    }
    public int CountActivitiesByMonth(String query, String status,String id){
        String[] columns = {
                KEY_ACTIVITY_TYPE,
                KEY_ACTIVITY,
                KEY_DATE,
                KEY_TIME,
                KEY_STATUS,
                KEY_ID1
        };
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITY, //Table to query
                columns,    //columns to return
                KEY_DATE+" LIKE ? AND "+KEY_STATUS+" = ? AND "+KEY_ID1+" =?",
                new String[] {"%"+query, status,id},
                null,
                null,
                null);

        int count=cursor.getCount();
        return count;

    }

    


}
