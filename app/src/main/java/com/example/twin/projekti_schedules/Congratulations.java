package com.example.twin.projekti_schedules;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Congratulations {

    public void showDialog(final Context context){

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.congratulations);

        Button dialog1Button=(Button)dialog.findViewById(R.id.dialogbtn3);

        dialog.show();
        dialog1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }});
    }
}
