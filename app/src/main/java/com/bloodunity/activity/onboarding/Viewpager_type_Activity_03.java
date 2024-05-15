package com.bloodunity.activity.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bloodunity.activity.LoginActivity;
import com.bloodunity.R;

public class Viewpager_type_Activity_03 extends AppCompatActivity {
    AppCompatButton btn_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_type03);
        //find ids...
        btn_getStarted = findViewById(R.id.btn_getStarted);

        // set on click on btn...
        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Viewpager_type_Activity_03.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}