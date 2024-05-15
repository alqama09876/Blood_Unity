package com.bloodunity.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.bloodunity.R;
import com.bloodunity.activity.LoginActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    TextView txt_tvName, txt_tvEmail, txt_tvPhone, txt_tvCity, txt_tvBlood;
    CircleImageView civ_profile;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String userID;
    AppCompatButton btn_logout;
    SwitchCompat switchCompat;
    boolean nightMODE;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //find ids...
        txt_tvName = view.findViewById(R.id.txt_tvName);
        txt_tvEmail = view.findViewById(R.id.txt_tvEmail);
        txt_tvPhone = view.findViewById(R.id.txt_tvPhone);
        txt_tvCity = view.findViewById(R.id.txt_tvCity);
        txt_tvBlood = view.findViewById(R.id.txt_tvBlood);
        civ_profile = view.findViewById(R.id.civ_profile);
        switchCompat = view.findViewById(R.id.switchCompat);


        // save data of particular user in the profile fragment...
        userID = auth.getCurrentUser().getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    txt_tvName.setText(document.getString("Name"));
                    txt_tvEmail.setText(document.getString("Email"));
                    txt_tvPhone.setText(document.getString("Phone"));
                    txt_tvCity.setText(document.getString("City"));
                    txt_tvBlood.setText(document.getString("BloodType"));
                    Glide.with(view.getContext()).load(document.getString("profile_image")).into(civ_profile);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // Logout btn work...
        btn_logout = view.findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });


        // for dark and light mode work...
//        getSupportActionBar().hide();
        sharedPreferences = view.getContext().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMODE = sharedPreferences.getBoolean("night", false);

        if (nightMODE) {
            switchCompat.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nightMODE) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", false);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("night", true);
                }
                editor.apply();
            }
        });
        return view;
    }
}

// UPDATE FEATURE...
// -----------------

// jese hi user signup pr apni daale ga ab usse update kerni hai apni info to wo dubara signup to nhi kerega to wo ye kareega k wo login
// kareega or profile fragment me jaker apni info jo bhi update kerni hai wo update kerlega...