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

public class ChangePassword extends AppCompatActivity {
    //Declaration EditTexts


    private EditText oldPwdEdit;
    private EditText newPwdEdit;
    private EditText cnfrmEdit;

    private Button submitBtn;

    private String oldPwd;
    private String newPwd;
    private String cnfrmPwd;

    private SqliteHelper update_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        setContentView(R.layout.changepass);
        //Intent intent2=getIntent();
        //final String name=intent2.getStringExtra("name");
        //Log.e("p",name);

        oldPwdEdit = (EditText)findViewById(R.id.editText1);
        newPwdEdit = (EditText)findViewById(R.id.editText2);
        cnfrmEdit  = (EditText)findViewById(R.id.editText3);

        submitBtn = (Button)findViewById(R.id.buttonSendKey);

        submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Intent intent =  new Intent(this, FaqjaKryesore.class);


                oldPwd = oldPwdEdit.getText().toString().trim();
                newPwd = newPwdEdit.getText().toString().trim();
                cnfrmPwd=cnfrmEdit.getText().toString().trim();

                if(oldPwd==null||"".equalsIgnoreCase(oldPwd)){
                    String header = "OLD PASSWORD REQUIRE";

                    Toast.makeText(getApplicationContext(),header,Toast.LENGTH_LONG).show();
                }
                else if(newPwd==null ||"".equalsIgnoreCase(newPwd)){
                    String header = "NEW PASSWORD IS REQUIRE";
                    Toast.makeText(getApplicationContext(), header,
                            Toast.LENGTH_LONG).show();
                }
                else if(cnfrmPwd==null ||"".equalsIgnoreCase(cnfrmPwd)){
                    String header = "COINFIRM PASSWORD IS REQUIRE";
                    Toast.makeText(getApplicationContext(), header,Toast.LENGTH_LONG).show();
                }
                else if(!newPwd.equalsIgnoreCase(cnfrmPwd)){
                    String header = "PASSWORD DOES NOT MATCH";
                    Toast.makeText(getApplicationContext(), header, Toast.LENGTH_LONG).show();
                }
                else{

                    update_user = new
                            SqliteHelper(getApplicationContext());

                    update_user.update_user(oldPwd,newPwd);

                    //startActivity(intent);


                }

            }
        });
    }

    }