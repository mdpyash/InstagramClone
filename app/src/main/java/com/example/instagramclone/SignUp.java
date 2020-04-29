package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;

    private EditText editName, editPunchSpeed, editPunchPower, editKickSpeed, editKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        editName = findViewById(R.id.editName);

        editPunchSpeed = findViewById(R.id.editPunchSpeed);
        editPunchPower = findViewById(R.id.editPunchPower);

        editKickSpeed = findViewById(R.id.editKickSpeed);
        editKickPower = findViewById(R.id.editKickPower);
    }

    @Override
    public void onClick(View viewButton) {

        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer");

            kickBoxer.put("name", editName.getText().toString());
            kickBoxer.put("punchSpeed", Integer.parseInt(editPunchSpeed.getText().toString()));
            kickBoxer.put("punchPower", Integer.parseInt(editPunchPower.getText().toString()));
            kickBoxer.put("kickSpeed", Integer.parseInt(editKickSpeed.getText().toString()));
            kickBoxer.put("kickPower", Integer.parseInt(editKickPower.getText().toString()));

            kickBoxer.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null){
                        //Toast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to the server.", Toast.LENGTH_LONG).show();
                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to the server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true ).show();
                    }else {
                        //Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    }

                }
            });
        } catch (Exception e){

            FancyToast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

        }

    }

    /*public void helloWorldTapped(View view){

        /*ParseObject boxer = new ParseObject("Boxer");

        boxer.put("punch_speed", 200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if(e == null){
                    Toast.makeText(SignUp.this, "Boxer object is saved successfully", Toast.LENGTH_LONG).show();
                }

            }
        });*/

        /* final ParseObject kickBoxer = new ParseObject("KickBoxer");

        kickBoxer.put("name", "John");
        kickBoxer.put("punchSpeed", 1000);
        kickBoxer.put("punchPower", 2000);
        kickBoxer.put("kickSpeed", 3000);
        kickBoxer.put("kickPower", 4000);

        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null){
                    Toast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to the server.", Toast.LENGTH_LONG).show();
                }

            }
        });*/

}

