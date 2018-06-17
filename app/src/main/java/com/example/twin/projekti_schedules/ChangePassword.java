package com.example.twin.projekti_schedules;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Settings.class));
                finish();
            }
        });


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
                    String header = "Old password is required";

                    Toast.makeText(getApplicationContext(),header,Toast.LENGTH_LONG).show();
                }
                else if(newPwd==null ||"".equalsIgnoreCase(newPwd)){
                    String header = "New password is required";
                    Toast.makeText(getApplicationContext(), header,
                            Toast.LENGTH_LONG).show();
                }
                else if(cnfrmPwd==null ||"".equalsIgnoreCase(cnfrmPwd)){
                    String header = "Confirm password is required";
                    Toast.makeText(getApplicationContext(), header,Toast.LENGTH_LONG).show();
                }
                else if(!newPwd.equalsIgnoreCase(cnfrmPwd)){
                    String header = "Passwords do not match";
                    Toast.makeText(getApplicationContext(), header, Toast.LENGTH_LONG).show();
                }
                else{
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            ChangePassword.this).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Change password alert");

                    // Setting Dialog Message
                    alertDialog.setMessage("Are you sure to change your password?");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.selected);

                    // Setting OK Button
                    alertDialog.setButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            update_user = new
                                    SqliteHelper(getApplicationContext());

                            update_user.update_user(oldPwd,newPwd);

                            //startActivity(intent);
                            PreferenceUtils.savePassword(null, ChangePassword.this);
                            PreferenceUtils.saveEmail(null, ChangePassword.this);
                            Intent intent1 = new Intent(ChangePassword.this, LoginActivity.class);
                            startActivity(intent1);
                            finish();
                            ChangePassword.this.finish();
                        }
                    });
                    alertDialog.setButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getApplicationContext(),ChangePassword.class));

                        }});

                    // Showing Alert Message
                    alertDialog.show();






                }

            }
        });
    }

}