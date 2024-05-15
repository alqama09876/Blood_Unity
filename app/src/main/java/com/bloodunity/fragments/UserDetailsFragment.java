package com.bloodunity.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bloodunity.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsFragment extends Fragment {  // implements UserAdapter.AdapterCallback

    CircleImageView civ_image;
    TextView tv_Name, tv_City, tv_Blood_type, tv_Location;

    //    ArrayList<UserModel> userArrayList = new ArrayList<>();
    AppCompatButton btn_contact;

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
//        find ids...
        civ_image = view.findViewById(R.id.civ_image);
        tv_Name = view.findViewById(R.id.tv_Name);
        tv_City = view.findViewById(R.id.tv_City);
        tv_Blood_type = view.findViewById(R.id.tv_Blood_type);
        tv_Location = view.findViewById(R.id.tv_Location);
        btn_contact = view.findViewById(R.id.btn_contact);


        // ...
        Bundle bundle = getArguments();
        if (bundle != null) {
            String name = bundle.getString("Name");
            String city = bundle.getString("City");
            String blood = bundle.getString("BloodType");
            String image = bundle.getString("profile_image");


            tv_Name.setText(name);
            tv_City.setText(city);
            tv_Blood_type.setText(blood);
            civ_image.setImageResource(Integer.parseInt(image));
        }


        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }


    private void showDialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_snackbar_layout);

        // find ids...
        LinearLayout ll_whatsapp = dialog.findViewById(R.id.ll_whatsapp);
        LinearLayout ll_call = dialog.findViewById(R.id.ll_call);


//         whatsapp option work...
        ll_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss(); // invisible...

                String phoneNumber = "03332893378";
                openWhatsapp(phoneNumber);

            }
        });

        // call option work...
        ll_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss(); // invisible...

                // Define the phone number you want to call
                String phoneNumber = "03123906199"; // Replace with the desired phone number

                // Create an intent with ACTION_DIAL
                Intent intent = new Intent(Intent.ACTION_DIAL);

                // Set the data (phone number) for the intent
                intent.setData(Uri.parse("tel:" + phoneNumber));

                // Start the intent to open the phone dialer
                startActivity(intent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void openWhatsapp(String number) {
        Uri uri = Uri.parse("smsto: " + number);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(intent, ""));
    }

}
