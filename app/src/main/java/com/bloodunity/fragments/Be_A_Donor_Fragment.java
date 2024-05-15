package com.bloodunity.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bloodunity.R;
import com.bloodunity.adapters.UserAdapter;
import com.bloodunity.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Be_A_Donor_Fragment extends Fragment {

    AppCompatButton btn_Apos, btn_Aneg, btn_Bneg, btn_Bpos, btn_Opos, btn_Oneg, btn_ABpos, btn_ABneg, btn_submit, btn_ok;
    EditText edt_full_name, edt_city, edt_location, edt_contact, edt_imageUrl;
    RecyclerView rv_users;
    ArrayList<UserModel> userArrayList = new ArrayList<>();

    public Be_A_Donor_Fragment() {
        // Required empty public constructor
    }

    @SuppressLint({"UseRequireInsteadOfGet", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_be__a__donor_, container, false);

        // find ids...
        btn_Apos = view.findViewById(R.id.btn_Apos);
        btn_Aneg = view.findViewById(R.id.btn_Aneg);
        btn_Bneg = view.findViewById(R.id.btn_Bneg);
        btn_Bpos = view.findViewById(R.id.btn_Bpos);
        btn_Oneg = view.findViewById(R.id.btn_Oneg);
        btn_Opos = view.findViewById(R.id.btn_Opos);
        btn_ABpos = view.findViewById(R.id.btn_ABpos);
        btn_ABneg = view.findViewById(R.id.btn_ABneg);
        edt_full_name = view.findViewById(R.id.edt_full_name);
        edt_city = view.findViewById(R.id.edt_city);
        edt_imageUrl = view.findViewById(R.id.edt_imageUrl);
        edt_location = view.findViewById(R.id.edt_location);
        edt_contact = view.findViewById(R.id.edt_contact);
        btn_submit = view.findViewById(R.id.btn_submit);

        // submit btn work...
        btn_submit.setOnClickListener(new View.OnClickListener() { // submit k btn pr click kerte hi ek dialog box open ho jis me (thanks you add successfully) ka dialog ho or jo bhi user details dale wo firebase pr save hojae...
            @Override
            public void onClick(View v) {
                String name = edt_full_name.getText().toString();
                String city = edt_city.getText().toString();
                String location = edt_location.getText().toString();
                String contact = edt_contact.getText().toString();
                String image = edt_imageUrl.toString();
                String A_pos = btn_Apos.getText().toString();
                String A_neg = btn_Aneg.getText().toString();
                String B_pos = btn_Bpos.getText().toString();
                String B_neg = btn_Bneg.getText().toString();
                String O_pos = btn_Opos.getText().toString();
                String O_neg = btn_Oneg.getText().toString();
                String AB_pos = btn_ABpos.getText().toString();
                String AB_neg = btn_ABneg.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(city) || TextUtils.isEmpty(location) || TextUtils.isEmpty(contact) ||
                        TextUtils.isEmpty(image) || TextUtils.isEmpty(A_pos) || TextUtils.isEmpty(A_neg) || TextUtils.isEmpty(B_pos) ||
                        TextUtils.isEmpty(B_neg) || TextUtils.isEmpty(O_pos) || TextUtils.isEmpty(O_neg) || TextUtils.isEmpty(AB_pos) ||
                        TextUtils.isEmpty(AB_neg)) {

                    edt_full_name.setError("Required*");
                    edt_city.setError("Required*");
                    edt_location.setError("Required*");
                    edt_imageUrl.setError("Required*");
                    edt_contact.setError("Required*");


                } else {
//                    Dialog dialog = new Dialog(getContext());
//                    dialog.setContentView(R.layout.custom_dialog);
//                    dialog.setCancelable(false);
//                    btn_ok = dialog.findViewById(R.id.btn_ok);
//
//
//                    btn_ok.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialog.dismiss();
//                        }
//                    });
//                    dialog.show();

                    insertData();

                }
            }
        });

        btn_Apos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "RestrictedApi"})
            @Override
            public void onClick(View v) {
                btn_Apos.setTextColor(getResources().getColor(R.color.white));
                btn_Apos.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        btn_Aneg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_Aneg.setTextColor(getResources().getColor(R.color.white));
                btn_Aneg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        btn_Bpos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_Bpos.setTextColor(getResources().getColor(R.color.white));
                btn_Bpos.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }

        });

        btn_Bneg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_Bneg.setTextColor(getResources().getColor(R.color.white));
                btn_Bneg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        btn_Opos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_Opos.setTextColor(getResources().getColor(R.color.white));
                btn_Opos.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        btn_Oneg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_Oneg.setTextColor(getResources().getColor(R.color.white));
                btn_Oneg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        btn_ABpos.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_ABpos.setTextColor(getResources().getColor(R.color.white));
                btn_ABpos.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        btn_ABneg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                btn_ABneg.setTextColor(getResources().getColor(R.color.white));
                btn_ABneg.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
            }
        });

        return view;
    }

    private void insertData() {
        String name = edt_full_name.getText().toString();
        String city = edt_city.getText().toString();
        String location = edt_location.getText().toString();
        String contact = edt_contact.getText().toString();
        String image = edt_imageUrl.toString();

        Map<String, Object> map = new HashMap<>();

        map.put("Name", name);
        map.put("City", city);
        map.put("Location", location);
        map.put("Phone", contact);
        map.put("profile_image", image);

        FirebaseFirestore.getInstance().collection("users").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                // If user enter data and click on submit button then the feild were empty...
                edt_full_name.setText("");
                edt_city.setText("");
                edt_location.setText("");
                edt_imageUrl.setText("");
                edt_contact.setText("");

                // set user in recycler view...
                rv_users = getView().findViewById(R.id.rv_users);
                UserAdapter userAdapter = new UserAdapter(getContext(), userArrayList);
                rv_users.setAdapter(userAdapter);
                rv_users.setLayoutManager(new LinearLayoutManager(getContext()));

                Toast.makeText(getContext(), "User Add Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Could Not Added " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}