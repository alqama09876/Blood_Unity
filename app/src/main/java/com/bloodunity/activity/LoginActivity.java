package com.bloodunity.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bloodunity.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button btn_signup, btn_do_acc, btn_login, btn_forget_pass;
    ImageButton fb, google;
    TextInputLayout txt_input_layout_email, txt_input_layout_password;
    TextInputEditText txtInput_edt_email, txtInput_edt_password;
    FirebaseAuth auth;
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "GOOGLEAUTH";
    GoogleSignInClient mGoogleSignInClient;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // find Ids...
        btn_signup = findViewById(R.id.btn_signup);
        fb = findViewById(R.id.fb);
        google = findViewById(R.id.google);
        btn_do_acc = findViewById(R.id.btn_do_acc);
        btn_login = findViewById(R.id.btn_login);
        txt_input_layout_email = findViewById(R.id.txt_input_layout_email);
        txt_input_layout_password = findViewById(R.id.txt_input_layout_password);
        txtInput_edt_email = findViewById(R.id.txtInput_edt_email);
        txtInput_edt_password = findViewById(R.id.txtInput_edt_password);
        btn_forget_pass = findViewById(R.id.btn_forget_pass);
        auth = FirebaseAuth.getInstance();


        // google sign-in....
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        dialog = new Dialog(LoginActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_wait);
        dialog.setCanceledOnTouchOutside(false);

//        google btn work...
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("http://www.google.com"));
//                startActivity(intent);

                signIn();

            }
        });


        // btn OnClick work...
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.facebook.com"));
                startActivity(intent);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_do_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Forget_Pass_Activity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Values...
                String email = Objects.requireNonNull(txtInput_edt_email.getText()).toString().trim();
                String password = Objects.requireNonNull(txtInput_edt_password.getText()).toString().trim();


                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//                    Toast.makeText(LoginActivity.this, "Empty Credentials...", Toast.LENGTH_SHORT).show();
                    txtInput_edt_email.setError("Required*");
                    txtInput_edt_password.setError("Required*");
                } else if (password.length() < 8) {
                    Toast.makeText(LoginActivity.this, "Password is too short!", Toast.LENGTH_SHORT).show();
                } else {
                    LoginUser(email, password);
                }
            }
        });

    }

    private void LoginUser(String txtInput_edt_email, String txtInput_edt_password) {
        auth.signInWithEmailAndPassword(txtInput_edt_email, txtInput_edt_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
//                FirebaseUser user = auth.getCurrentUser();
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, Bottom_Navigation_Activity.class));
                finish();
            }
        });
    }


    //    Google Sign In Methods...
    private void signIn() { // custom method...
        Intent intent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            dialog.show();
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());

            } catch (ApiException e) {
                Log.w(TAG, "Google Sign-In Failed", e);
//                throw new RuntimeException;
            }
            dialog.dismiss();
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    Intent intent = new Intent(LoginActivity.this, Bottom_Navigation_Activity.class);
                    startActivity(intent);
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}


