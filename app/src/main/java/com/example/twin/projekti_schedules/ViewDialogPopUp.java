package com.example.twin.projekti_schedules;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Window;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class ViewDialogPopUp {
    public void showDialog(Activity activity, String msg, String msg2){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popup);



        TextView text = (TextView) dialog.findViewById(R.id.txtquote);
        TextView txtAuthor=(TextView) dialog.findViewById(R.id.txtAuthor);
        text.setText(msg);
        txtAuthor.setText(msg2);

        Button dialogButton = (Button) dialog.findViewById(R.id.btnthanks);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent=new Intent(this,FaqjaKryesore.class);
                startActivity();

            }
        });

        dialog.show();

    }
}
