package com.bloodunity.activity.otp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.bloodunity.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Verification_Activity1 extends AppCompatActivity {

    EditText ent_phone_no;
    AppCompatButton btn_get_otp;
    ProgressBar pgBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification1);

        ent_phone_no = findViewById(R.id.ent_phone_no);
        btn_get_otp = findViewById(R.id.btn_get_otp);
        pgBar = findViewById(R.id.pgBar);

        btn_get_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_no = ent_phone_no.getText().toString().trim();

                if (phone_no.isEmpty()) {
                    Toast.makeText(Otp_Verification_Activity1.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }

                pgBar.setVisibility(View.VISIBLE);
                btn_get_otp.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92" + ent_phone_no.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                        Otp_Verification_Activity1.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                pgBar.setVisibility(View.GONE);
                                btn_get_otp.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                pgBar.setVisibility(View.GONE);
                                btn_get_otp.setVisibility(View.VISIBLE);
                                Toast.makeText(Otp_Verification_Activity1.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                pgBar.setVisibility(View.GONE);
                                btn_get_otp.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(Otp_Verification_Activity1.this, Otp_Verification_Activity2.class);
                                intent.putExtra("mobile", ent_phone_no.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                startActivity(intent);
                            }
                        }
                );


//                if (!phone_no.isEmpty()){
//                    if (phone_no.length() == 10){
//                        Intent intent = new Intent(Otp_Verification_Activity1.this, Otp_Verification_Activity2.class);
//                        intent.putExtra("mobile", phone_no);
//                        startActivity(intent);
//                    }
//                    else {
//                        Toast.makeText(Otp_Verification_Activity1.this, "Please Enter Correct Number", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//                    Toast.makeText(Otp_Verification_Activity1.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }
}