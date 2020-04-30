package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnGetAllData, btnNextActivity;

    private EditText editName, editPunchSpeed, editPunchPower, editKickSpeed, editKickPower;

    private TextView txtGetData;

    private String allKickBoxers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(SignUp.this);

        btnGetAllData = findViewById(R.id.btnGetAllData);

        btnNextActivity = findViewById(R.id.btnNextActivity);

        editName = findViewById(R.id.editName);

        editPunchSpeed = findViewById(R.id.editPunchSpeed);
        editPunchPower = findViewById(R.id.editPunchPower);

        editKickSpeed = findViewById(R.id.editKickSpeed);
        editKickPower = findViewById(R.id.editKickPower);

        txtGetData = findViewById(R.id.txtGetData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");

                parseQuery.getInBackground("eCna74TPLq", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null){
                            txtGetData.setText(object.get("name") + " - " + "Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers = "";

                ParseQuery<ParseObject> getAllQuery = ParseQuery.getQuery("KickBoxer");

                //getAllQuery.whereGreaterThan("punchPower ",2000);
                getAllQuery.whereGreaterThanOrEqualTo("punchPower", 2000);
                getAllQuery.setLimit(1);

                getAllQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null){
                            if (objects.size() > 0){
                                for (ParseObject kickBoxer: objects){
                                    allKickBoxers = allKickBoxers + kickBoxer.get("name") + "\n";
                                }
                                FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            }
                        }
                    }
                });
            }
        });

        btnNextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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

