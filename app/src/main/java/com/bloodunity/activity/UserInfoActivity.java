package com.bloodunity.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bloodunity.R;
import com.bloodunity.fragments.MapsFragment;
import com.bloodunity.fragments.UserDetailsFragment;
import com.bloodunity.models.UserModel;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity { // implements UserAdapter.OnItemClickListener

//    CircleImageView civ_image;
//    TextView tv_Name, tv_City, tv_Blood_type, tv_Location;
//    AppCompatButton btn_contact;
    ArrayList<UserModel> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);


        // For Google Map And User Details...
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.GoogleMap, new MapsFragment());
        fragmentTransaction.add(R.id.UserDetails, new UserDetailsFragment());
        fragmentTransaction.commit();

    }

//    @Override
//    public void onItemClick(int index) {
//        Bundle bundle = new Bundle();
//        bundle.putString("Name", userArrayList.get(index).Name);
//        bundle.putString("City", userArrayList.get(index).City);
//        bundle.putString("BloodType", userArrayList.get(index).BloodType);
//        bundle.putString("profile_image", userArrayList.get(index).profile_image);
//
//        UserDetailsFragment userDetailsFragment = new UserDetailsFragment();
//        userDetailsFragment.setArguments(bundle);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.UserDetails, userDetailsFragment);
//    }


}