package com.example.mehdi.footballapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(SplashScreen.this,
                            SignIn.class));
                    finish();
                }
            };
        };
        splashTread.start();
    }
}