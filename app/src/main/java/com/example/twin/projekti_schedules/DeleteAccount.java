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


                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DeleteAccount.this);



                    // Setting Dialog Title
                    alertDialog.setTitle("Change password alert");

                    // Setting Dialog Message
                    alertDialog.setMessage("Are you sure to change your password?");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.selected);

                    // Setting OK Button
                    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            deleteacc = new
                                    SqliteHelper(getApplicationContext());

                            deleteacc.delete_user(pwdS,emailS);

                            // startActivity(intent);
                            PreferenceUtils.savePassword(null, DeleteAccount.this);
                            PreferenceUtils.saveEmail(null, DeleteAccount.this);
                            Intent intent1 = new Intent(DeleteAccount.this, LoginActivity.class);
                            intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent1);
                            finish();


                        }
                    });
                    alertDialog.setNegativeButton("No",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(getApplicationContext(),DeleteAccount.class));
                            dialog.dismiss();

                        }});

                    // Showing Alert Message
                    alertDialog.show();



                }

            }
        });
    }

}