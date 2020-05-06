package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    //UI Components
    private EditText edtTxtEmailSignUp, edtTxtUserNameSignUp, edtTxtPasswordSignUp;
    private Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        //Variable declaration
        edtTxtEmailSignUp = findViewById(R.id.edtTxtEmailSignUp);
        edtTxtUserNameSignUp = findViewById(R.id.edtTxtUserNameSignUp);
        edtTxtPasswordSignUp =findViewById(R.id.edtTxtPasswordSignUp);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnSignUp.setOnClickListener(SignUp.this);
        btnLogIn.setOnClickListener(SignUp.this);

        if(ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSignUp:

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtTxtEmailSignUp.getText().toString());
                appUser.setUsername(edtTxtUserNameSignUp.getText().toString());
                appUser.setPassword(edtTxtPasswordSignUp.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Signin up " + edtTxtUserNameSignUp.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
                        }else{
                            FancyToast.makeText(SignUp.this,"There was an error: " + e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                        }

                        progressDialog.dismiss();
                    }
                });

                break;
            case R.id.btnLogIn:

                Intent intent = new Intent (SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;

        }

    }
}

