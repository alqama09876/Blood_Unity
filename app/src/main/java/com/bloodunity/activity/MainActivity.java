package com.bloodunity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bloodunity.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    AppCompatButton logout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
    }

    public void Logout(){
        FirebaseAuth.getInstance().signOut(); // is se ye login activity se sign-out kerdega...
        Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
    }
}


// Develop these Screen...
//------------------------
// profile (show the data of user) --> Java file or fragment file , (fragment + recycler view) in xml
// Verification (when user put email)
// Navigation Drawer
// Blood group screen (fina a donor, location, blood groups)
// Home Screen


//
//<!--    <LinearLayout-->
//<!--        android:layout_marginTop="@dimen/_20sdp"-->
//<!--        android:layout_below="@id/btn_login"-->
//<!--        android:layout_width="match_parent"-->
//<!--        android:layout_height="wrap_content"-->
//<!--        android:orientation="horizontal">-->
//<!--    <View-->
//<!--        android:id="@+id/view1"-->
//<!--        android:layout_width="wrap_content"-->
//<!--        android:layout_height="wrap_content"-->
//<!--        android:layout_marginTop="@dimen/_20sdp" />-->
//
//<!--        <TextView-->
//<!--            android:layout_width="wrap_content"-->
//<!--            android:layout_height="wrap_content"-->
//<!--            />-->
//
//<!--    </LinearLayout>-->
//
//
//<!--    <EditText-->
//<!--        android:id="@+id/edt_email"-->
//<!--        android:layout_width="match_parent"-->
//<!--        android:layout_height="wrap_content"-->
//<!--        android:layout_below="@id/view"-->
//<!--        android:layout_marginStart="@dimen/_15sdp"-->
//<!--        android:layout_marginTop="@dimen/_10sdp"-->
//<!--        android:layout_marginEnd="@dimen/_15sdp"-->
//<!--        android:autofillHints="emailAddress"-->
//<!--        android:fontFamily="@font/poppins_light"-->
//<!--        android:hint="@string/email"-->
//<!--        android:inputType="textEmailAddress"-->
//<!--        android:textColorHint="@color/black"-->
//<!--        android:textSize="@dimen/_15sdp" />-->
//
//<!--    <EditText-->
//<!--        android:id="@+id/edt_password"-->
//<!--        android:layout_width="match_parent"-->
//<!--        android:layout_height="wrap_content"-->
//<!--        android:layout_below="@id/edt_email"-->
//<!--        android:layout_marginStart="@dimen/_15sdp"-->
//<!--        android:layout_marginTop="@dimen/_10sdp"-->
//<!--        android:layout_marginEnd="@dimen/_15sdp"-->
//<!--        android:autofillHints="password"-->
//<!--        android:fontFamily="@font/poppins_light"-->
//<!--        android:hint="@string/password"-->
//<!--        android:inputType="textPassword"-->
//<!--        android:textColorHint="@color/black"-->
//<!--        android:textSize="@dimen/_15sdp" />-->
//
//<!--    <Button-->
//<!--        android:id="@+id/btn_forget_pass"-->
//<!--        android:layout_width="wrap_content"-->
//<!--        android:layout_height="wrap_content"-->
//<!--        android:layout_below="@+id/edt_password"-->
//<!--        android:layout_alignEnd="@id/edt_password"-->
//<!--        android:layout_marginTop="@dimen/_10sdp"-->
//<!--        android:fontFamily="@font/poppins_med"-->
//<!--        android:text="@string/f_password"-->
//<!--        android:textColor="@color/red" />-->
//
//<!--    <Button-->
//<!--        android:id="@+id/btn_login"-->
//<!--        android:layout_width="@dimen/_280sdp"-->
//<!--        android:layout_height="@dimen/_40sdp"-->
//<!--        android:layout_below="@+id/btn_forget_pass"-->
//<!--        android:layout_marginStart="@dimen/_15sdp"-->
//<!--        android:layout_marginTop="@dimen/_20sdp"-->
//<!--        android:layout_marginEnd="@dimen/_15sdp"-->
//<!--        android:backgroundTint="@color/red"-->
//<!--        android:text="@string/login"-->
//<!--        android:textColor="@color/white" />-->