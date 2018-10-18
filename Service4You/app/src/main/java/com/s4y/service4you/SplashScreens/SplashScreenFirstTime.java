package com.s4y.service4you.SplashScreens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.s4y.service4you.Authentication.LoginActivity;
import com.s4y.service4you.R;

public class SplashScreenFirstTime extends AppCompatActivity {

    private Button btnNext;

    private SharedPreferences firstTimePreferences;
    private SharedPreferences loginPreferencens;
    private Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_firsttime);

        firstTimePreferences = getSharedPreferences("FirstTimePref",MODE_PRIVATE);
        loginPreferencens = getSharedPreferences("Login",MODE_PRIVATE);

        btnNext = findViewById(R.id.btnNextSplash);
        SharedPreferences.Editor editorfirstTime = firstTimePreferences.edit();

        firstTime = firstTimePreferences.getBoolean("firstTime",true);

        if(firstTime){
            firstTime = false;
            editorfirstTime.putBoolean("firstTime",firstTime);
            editorfirstTime.apply();
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(SplashScreenFirstTime.this, LoginActivity.class);
                    startActivity(i);
                    SplashScreenFirstTime.this.finish();
                }
            });
        }else if(loginPreferencens.getAll()==null){
            Intent i = new Intent(SplashScreenFirstTime.this, LoginActivity.class);
            startActivity(i);
            SplashScreenFirstTime.this.finish();
        }else {
            Intent i = new Intent(SplashScreenFirstTime.this, SplashScreen.class);
            startActivity(i);
            SplashScreenFirstTime.this.finish();
        }

    }
}
