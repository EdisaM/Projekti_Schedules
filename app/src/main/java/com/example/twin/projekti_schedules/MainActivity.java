package com.example.twin.projekti_schedules;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button _loginbutton;
    EditText _FirstName, _LastName, _Email, _Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper= new DatabaseHelper(this);
        _loginbutton=(Button)findViewById(R.id.loginbutton);
        _FirstName=(EditText)findViewById(R.id.FirstName);
        _LastName=(EditText)findViewById(R.id.LastName);
        _Email=(EditText)findViewById(R.id.Email);
        _Password=(EditText)findViewById(R.id.Password);

        _loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=openHelper.getWritableDatabase();
                String FirstName=_FirstName.getText().toString();
                String LastName=_LastName.getText().toString();
                String Email=_Email.getText().toString();
                String Password=_Password.getText().toString();
                insertData(FirstName,LastName,Email,Password);
                Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
                

            }
        });

    }
    public void insertData(String FirstName, String LastName, String Email, String Password){
        ContentValues contentValues= new ContentValues(); //this class is to write values in the database
        contentValues.put(DatabaseHelper.COL_2, FirstName);
        contentValues.put(DatabaseHelper.COL_3, LastName);
        contentValues.put(DatabaseHelper.COL_4, Email);
        contentValues.put(DatabaseHelper.COL_5, Password);
        long id= db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);

    }
}
