package com.bloodunity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bloodunity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_Pass_Activity extends AppCompatActivity {

    TextInputLayout txt_input_layout_email;
    TextInputEditText txt_input_edt_email;
    AppCompatButton btn_next;
    ImageButton btn_previous;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        // find ids...
        txt_input_layout_email = findViewById(R.id.txt_input_layout_email);
        txt_input_edt_email = findViewById(R.id.txt_input_edt_email);
        btn_next = findViewById(R.id.btn_next);
        btn_previous = findViewById(R.id.btn_previous);
        auth = FirebaseAuth.getInstance();

        // btn OnCLick Work...
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forget_Pass_Activity.this, LoginActivity.class));
            }
        });

        // btn onClick work...
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String email = txt_input_edt_email.getText().toString();
                        if (email.isEmpty()) {
                            txt_input_edt_email.setError("Required*");
                        } else {
                            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Forget_Pass_Activity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(Forget_Pass_Activity.this, "Error " + task.getException(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Forget_Pass_Activity.this, "Fail " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        startActivity(new Intent(Forget_Pass_Activity.this, LoginActivity.class));
                    }
                });
            }
        });
    }
}