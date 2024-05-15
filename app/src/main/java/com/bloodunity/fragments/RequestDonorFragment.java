package com.bloodunity.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bloodunity.R;

public class RequestDonorFragment extends Fragment {

    Spinner spn_gender;
    AppCompatButton btn_reqDonor, btn_Apos, btn_Aneg, btn_Bneg, btn_Bpos, btn_Opos, btn_Oneg, btn_ABpos, btn_ABneg;
    EditText edt_location, edt_patient;

    TextView txt_time, txt_date;

    public RequestDonorFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_donor, container, false);

        // find ids...
        spn_gender = view.findViewById(R.id.spn_gender);
        btn_Apos = view.findViewById(R.id.btn_Apos);
        btn_Aneg = view.findViewById(R.id.btn_Aneg);
        btn_Bneg = view.findViewById(R.id.btn_Bneg);
        btn_Bpos = view.findViewById(R.id.btn_Bpos);
        btn_Oneg = view.findViewById(R.id.btn_Oneg);
        btn_Opos = view.findViewById(R.id.btn_Opos);
        btn_ABpos = view.findViewById(R.id.btn_ABpos);
        btn_ABneg = view.findViewById(R.id.btn_ABneg);
        edt_location = view.findViewById(R.id.edt_location);
        edt_patient = view.findViewById(R.id.edt_patient);
        txt_time = view.findViewById(R.id.txt_time);
        txt_date = view.findViewById(R.id.txt_date);
        btn_reqDonor = view.findViewById(R.id.btn_reqDonor);


        // GENDER SPINNER WORK...
        // ----------------------

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.gender_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spn_gender.setAdapter(adapter);


        // Button work...
        btn_reqDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = edt_location.getText().toString();
                String patient = edt_patient.getText().toString();
                String time = txt_time.getText().toString();
                String date = txt_date.getText().toString();
                String A_pos = btn_Apos.getText().toString();
                String A_neg = btn_Aneg.getText().toString();
                String B_pos = btn_Bpos.getText().toString();
                String B_neg = btn_Bneg.getText().toString();
                String O_pos = btn_Opos.getText().toString();
                String O_neg = btn_Oneg.getText().toString();
                String AB_pos = btn_ABpos.getText().toString();
                String AB_neg = btn_ABneg.getText().toString();

                if (TextUtils.isEmpty(location) || TextUtils.isEmpty(patient) || TextUtils.isEmpty(time) || TextUtils.isEmpty(date) || TextUtils.isEmpty(A_pos) || TextUtils.isEmpty(A_neg) || TextUtils.isEmpty(B_pos) ||
                        TextUtils.isEmpty(B_neg) || TextUtils.isEmpty(O_pos) || TextUtils.isEmpty(O_neg) || TextUtils.isEmpty(AB_pos) ||
                        TextUtils.isEmpty(AB_neg)) {

                    edt_location.setError("Required*");
                    edt_patient.setError("Required*");
                    txt_time.setError("Required*");
                    txt_date.setError("Required*");

                    Toast.makeText(getContext(), "Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
//                    requestData();
                    Toast.makeText(getContext(), "Request Submitted Successfully", Toast.LENGTH_SHORT).show();
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

//    private void requestData() {
//        String location = edt_location.getText().toString();
//        String patient = edt_patient.getText().toString();
//        String time = txt_time.getText().toString();
//        String date = txt_date.getText().toString();
//        String A_pos = btn_Apos.getText().toString();
//        String A_neg = btn_Aneg.getText().toString();
//        String B_pos = btn_Bpos.getText().toString();
//        String B_neg = btn_Bneg.getText().toString();
//        String O_pos = btn_Opos.getText().toString();
//        String O_neg = btn_Oneg.getText().toString();
//        String AB_pos = btn_ABpos.getText().toString();
//        String AB_neg = btn_ABneg.getText().toString();
//    }
}