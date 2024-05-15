package com.bloodunity.activity.otp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bloodunity.activity.LoginActivity;
import com.bloodunity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Verification_Activity2 extends AppCompatActivity {

    EditText edt1, edt2, edt3, edt4, edt5, edt6;
    AppCompatButton btn_verify, resend_otp;
    TextView tv_number;
    ProgressBar pgBar;
    String verificationId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_otp_verification);

//        find ids...
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        edt5 = findViewById(R.id.edt5);
        edt6 = findViewById(R.id.edt6);
        tv_number = findViewById(R.id.tv_number);
        btn_verify = findViewById(R.id.btn_verify);
        resend_otp = findViewById(R.id.resend_otp);
        pgBar = findViewById(R.id.pgBar);

        numberMove(); // custom function for move the edittext...

        // Number textview work...
        tv_number.setText(String.format("+92-%s", getIntent().getStringExtra("mobile")));
        verificationId = getIntent().getStringExtra("verificationId");

        // Submit btn work...
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edt_1 = edt1.getText().toString().trim();
                String edt_2 = edt2.getText().toString().trim();
                String edt_3 = edt3.getText().toString().trim();
                String edt_4 = edt4.getText().toString().trim();
                String edt_5 = edt5.getText().toString().trim();
                String edt_6 = edt6.getText().toString().trim();

                if (edt_1.isEmpty() || edt_2.isEmpty() || edt_3.isEmpty() || edt_4.isEmpty() || edt_5.isEmpty() || edt_6.isEmpty()) {
                    Toast.makeText(Otp_Verification_Activity2.this, "Enter Valid OTP code", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = edt_1 + edt_2 + edt_3 + edt_4 + edt_5 + edt_6;

                if (verificationId != null) {
                    pgBar.setVisibility(View.VISIBLE);
                    btn_verify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId, code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            pgBar.setVisibility(View.GONE);
                            btn_verify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(Otp_Verification_Activity2.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Otp_Verification_Activity2.this, "The Verification code was entered is invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

//                if (!edt_1.isEmpty() && !edt_2.isEmpty() && !edt_3.isEmpty() && !edt_4.isEmpty() && !edt_5.isEmpty() && !edt_6.isEmpty()) {
//                    Toast.makeText(Otp_Verification_Activity2.this, "OTP VERIFY", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(Otp_Verification_Activity2.this, "Please Enter All Numbers", Toast.LENGTH_SHORT).show();
//                }
            }
        });

//        numberMove(); // custom function for move the edittext...

        // Resend btn work...
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        Otp_Verification_Activity2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Otp_Verification_Activity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                verificationId = newVerificationId;
                                Toast.makeText(Otp_Verification_Activity2.this, "OTP Send", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

//        numberMove(); // custom function for move the edittext...
    }

    private void numberMove() {
        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    edt2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    edt3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    edt4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    edt5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    edt6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}