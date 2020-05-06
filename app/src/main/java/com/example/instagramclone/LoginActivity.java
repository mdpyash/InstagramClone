package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtTxtEmailLogin, edtTxtPasswordLogin;
    private Button btnActivityLogIn, btnActivityLoginSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtTxtEmailLogin = findViewById(R.id.edtTxtEmailLogin);
        edtTxtPasswordLogin = findViewById(R.id.edtTxtPasswordLogin);

        btnActivityLogIn = findViewById(R.id.btnActivityLogIn);
        btnActivityLoginSignUp = findViewById(R.id.btnActivityLoginSignUp);

        btnActivityLogIn.setOnClickListener(LoginActivity.this);
        btnActivityLoginSignUp.setOnClickListener(LoginActivity.this);

        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnActivityLogIn:
                ParseUser.logInInBackground(edtTxtEmailLogin.getText().toString(), edtTxtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null && e == null){
                            FancyToast.makeText(LoginActivity.this, user.getUsername() + " is logged in successfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                        }
                    }
                });
                break;
            case R.id.btnActivityLoginSignUp:

                break;
        }

    }
}
