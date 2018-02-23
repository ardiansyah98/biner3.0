package com.ilkom.biner.biner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {


    private static int splashInterval = 2000;

    public static final String myPrefs = "MyPrefs" ;
    public static final String status = "isLogin";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedpreferences = getSharedPreferences(myPrefs, Context.MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedpreferences.getBoolean(status, false)){
                    Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }, splashInterval);
    }
}
