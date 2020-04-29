package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9hrxli3hmoiJ7TkRGEisYg2y9u6VZb5xU17EoXZC")
                // if defined
                .clientKey("fFZKIysc0chhDsaGQnKojdB7PF2d6y0rHDtTu63Y")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
