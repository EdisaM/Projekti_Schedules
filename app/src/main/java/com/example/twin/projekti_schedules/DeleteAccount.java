package com.example.twin.projekti_schedules;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAccount extends AppCompatActivity {
    //Declaration EditTexts


    private EditText emailEdit;
    private EditText pwdEdit;

    private Button submitBtn;

    private String emailS;
    private String pwdS;


    private SqliteHelper deleteacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.deleteacc);
        //Intent intent2=getIntent();
        //final String name=intent2.getStringExtra("name");
        //Log.e("p",name);

        emailEdit = (EditText)findViewById(R.id.editText1);
        pwdEdit = (EditText)findViewById(R.id.editText2);


        submitBtn = (Button)findViewById(R.id.buttonSendKey);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                emailS = emailEdit.getText().toString().trim();
                pwdS = pwdEdit.getText().toString().trim();

                if(emailS==null||"".equalsIgnoreCase(emailS)){
                    String header = "Email is required";

                    Toast.makeText(getApplicationContext(),header,Toast.LENGTH_LONG).show();
                }
                else if(pwdS==null ||"".equalsIgnoreCase(pwdS)){
                    String header = "Password is required";
                    Toast.makeText(getApplicationContext(), header,
                            Toast.LENGTH_LONG).show();
                }

                else{

                    deleteacc = new
                            SqliteHelper(getApplicationContext());

                    deleteacc.delete_user(pwdS,emailS);

                   // startActivity(intent);


                }

            }
        });
    }

}