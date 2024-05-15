package com.bloodunity.activity.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bloodunity.R;

public class Viewpager_type_Activity_01 extends AppCompatActivity {

    Button btn_next;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_type01);

        btn_next = findViewById(R.id.btn_next);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Viewpager_type_Activity_01.this, Viewpager_type_Activity_02.class);
                startActivity(intent);
            }
        });
    }
}