package com.bloodunity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bloodunity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    ImageButton iv_previous;
    TextInputLayout txt_input_layout_name, txt_input_layout_phoneno, txt_input_layout_city, txt_input_layout_email, txt_input_layout_password, txt_input_layout_confirm_password, txt_input_layout_blood_type, txt_input_layout_location;
    TextInputEditText txtInput_edt_name, txtInput_edt_phoneno, txtInput_edt_city, txtInput_edt_email, txtInput_edt_password, txtInput_edt_confirm_password, txtInput_edt_blood_type, txtInput_edt_location;

    CircleImageView profile_image;
    Button btn_register, btn_acc;
    FirebaseAuth auth;
    FirebaseFirestore db;
    String userID;

    FirebaseStorage storage;
    StorageReference reference;
    StorageReference Profile_Ref;
    int REQUEST_CODE = 1;
    Uri imageURI;
    String profileUrl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // find ids...
        iv_previous = findViewById(R.id.iv_previous);
        txt_input_layout_name = findViewById(R.id.txt_input_layout_name);
        txt_input_layout_phoneno = findViewById(R.id.txt_input_layout_phoneno);
        txt_input_layout_city = findViewById(R.id.txt_input_layout_city);
        txt_input_layout_email = findViewById(R.id.txt_input_layout_email);
        txt_input_layout_password = findViewById(R.id.txt_input_layout_password);
        txt_input_layout_confirm_password = findViewById(R.id.txt_input_layout_confirm_password);
        txt_input_layout_blood_type = findViewById(R.id.txt_input_layout_blood_type);
        txt_input_layout_location = findViewById(R.id.txt_input_layout_location);
        txtInput_edt_name = findViewById(R.id.txtInput_edt_name);
        txtInput_edt_phoneno = findViewById(R.id.txtInput_edt_phoneno);
        txtInput_edt_city = findViewById(R.id.txtInput_edt_city);
        txtInput_edt_email = findViewById(R.id.txtInput_edt_email);
        txtInput_edt_password = findViewById(R.id.txtInput_edt_password);
        txtInput_edt_confirm_password = findViewById(R.id.txtInput_edt_confirm_password);
        txtInput_edt_blood_type = findViewById(R.id.txtInput_edt_blood_type);
        txtInput_edt_location = findViewById(R.id.txtInput_edt_location);
        profile_image = findViewById(R.id.profile_image);
        btn_register = findViewById(R.id.btn_register);
        btn_acc = findViewById(R.id.btn_acc);
//        switcher = findViewById(R.id.switcher);


        // Firebase Authentication...
        auth = FirebaseAuth.getInstance(); // getInstance() is a class..

        //Firestore...
        db = FirebaseFirestore.getInstance();

        //storage...
        storage = FirebaseStorage.getInstance();
        reference = storage.getReference();
        Profile_Ref = reference.child("profile/" + FirebaseAuth.getInstance().getCurrentUser());

//                .child("user images");

        // btn OnCLick Work...
        iv_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        // have acc btn work...
        btn_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ImagePicker = new Intent(Intent.ACTION_PICK);
                ImagePicker.setType("image/*");
                startActivityForResult(ImagePicker, REQUEST_CODE);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Values...
                String email = Objects.requireNonNull(txtInput_edt_email.getText()).toString().trim();
                String password = Objects.requireNonNull(txtInput_edt_password.getText()).toString().trim();
                String confirm_password = Objects.requireNonNull(txtInput_edt_confirm_password.getText()).toString().trim();
                String name = Objects.requireNonNull(txtInput_edt_name.getText()).toString().trim();
                String phone = Objects.requireNonNull(txtInput_edt_phoneno.getText()).toString().trim();
                String city = Objects.requireNonNull(txtInput_edt_city.getText()).toString().trim();
                String bloodType = Objects.requireNonNull(txtInput_edt_blood_type.getText()).toString().trim();
                String location = Objects.requireNonNull(txtInput_edt_location.getText()).toString().trim();


                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirm_password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(city) || TextUtils.isEmpty(bloodType) || TextUtils.isEmpty(location)) {
//                    Toast.makeText(RegisterActivity.this, "Empty Credentials...", Toast.LENGTH_SHORT).show();
                    txtInput_edt_email.setError("Required*");
                    txtInput_edt_password.setError("Required*");
                    txtInput_edt_confirm_password.setError("Required*");
                    txtInput_edt_name.setError("Required*");
                    txtInput_edt_phoneno.setError("Required*");
                    txtInput_edt_city.setError("Required*");
                    txtInput_edt_blood_type.setError("Required*");
                    txtInput_edt_location.setError("Required*");
                } else if (password.length() < 10) {
                    Toast.makeText(RegisterActivity.this, "Password is too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registeredUser(email, password, confirm_password);
                }
            }
        });

    }

    private void registeredUser(String email, String password, String confirm_password) {
        if (password.equals(txtInput_edt_confirm_password.getText().toString())) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registration Successfully!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // for current user...
                        userID = auth.getCurrentUser().getUid(); // userID
                        saveUserData(user.getUid(), profileUrl);
                        uploadUserProfile();
                        System.out.println("User id is: " + user.getUid()); // for get the id from firebase...
//                        System.out.println("Profile URL is:" +profileUrl);
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RegisterActivity.this, "Fail " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // for image...
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                try {
                    imageURI = data.getData(); // to get the image url...
                    final InputStream imageStream = getContentResolver().openInputStream(imageURI);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    profile_image.setImageBitmap(selectedImage);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "You haven't Picked Image", Toast.LENGTH_SHORT).show();
            }
        }
    }


    // upload image on Firebase storage...
    private void uploadUserProfile() {
        System.out.println("Image URI " + imageURI);

        // upload image in the firestore...
        Profile_Ref.putFile(imageURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        profileUrl = task.getResult().toString();
                        FirebaseUser user = auth.getCurrentUser();
//                        Toast.makeText(RegisterActivity.this, "URl is " + profileUrl, Toast.LENGTH_SHORT).show();
                        saveUserData(user.getUid(), profileUrl);
//                        System.out.println("Profile URL is: " + profileUrl);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "URL not found " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveUserData(String userID, String profileUrl) {

        String name = txtInput_edt_name.getText().toString().trim();
        String email = txtInput_edt_email.getText().toString().trim();
        String phone = txtInput_edt_phoneno.getText().toString().trim();
        String city = txtInput_edt_city.getText().toString().trim();
        String bloodType = txtInput_edt_blood_type.getText().toString().trim();
        String location = txtInput_edt_location.getText().toString().trim();


        DocumentReference documentReference = db.collection("users").document(userID);
        // save user another data...
        Map<String, Object> user = new HashMap<>();

        user.put("Name", name);
        user.put("Phone", phone);
        user.put("Email", email);
        user.put("City", city);
        user.put("BloodType", bloodType);
        user.put("Location", location);
        user.put("UserID", userID);
        user.put("profile_image", profileUrl);

        System.out.println("User Data is: " + user);

        documentReference.set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RegisterActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, "Failed to upload", Toast.LENGTH_SHORT).show();
            }
        });


        // Previous work then update to docRef...
        // --------------------------------------

//        // data upload or save in the firestore...
//        db.collection("users").document(userID).set(user_data).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(RegisterActivity.this, "Data Upload Successfully", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RegisterActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}