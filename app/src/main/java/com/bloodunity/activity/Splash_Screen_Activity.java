package com.bloodunity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bloodunity.R;
import com.bloodunity.activity.onboarding.Viewpager_type_Activity_01;

public class Splash_Screen_Activity extends AppCompatActivity {

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Splash Screen Code...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Screen_Activity.this, Viewpager_type_Activity_01.class);
                startActivity(intent);
            }
        }, 2000);
    }
}