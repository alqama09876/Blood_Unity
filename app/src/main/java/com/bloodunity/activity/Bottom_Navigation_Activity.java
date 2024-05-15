package com.bloodunity.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bloodunity.R;
import com.bloodunity.fragments.Be_A_Donor_Fragment;
import com.bloodunity.fragments.Find_Donor_Fragment;
import com.bloodunity.fragments.HomeScreenFragment;
import com.bloodunity.fragments.ProfileFragment;
import com.bloodunity.fragments.RequestDonorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Bottom_Navigation_Activity extends AppCompatActivity {

    BottomNavigationView bn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        // set Recycler view in Home fragment...
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.container, new HomeScreenFragment());
//        fragmentTransaction.commit();


        //find ids...
        bn_view = findViewById(R.id.bn_view);

        bn_view.setSelectedItemId(R.id.bnav_home); // by default this fragment will open...

        LoadFragment(new Find_Donor_Fragment(), 0); // by default this fragment will open...

        bn_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId(); // this will get all the item ids...
                if (id == R.id.bnav_home) {
                    LoadFragment(new HomeScreenFragment(), 1);
                } else if (id == R.id.bnav_request_donor) {
                    LoadFragment(new RequestDonorFragment(), 1);
                } else if (id == R.id.bnav_find_donor) {
                    LoadFragment(new Find_Donor_Fragment(), 1);
                } else if (id == R.id.bnav_be_a_donor) {
                    LoadFragment(new Be_A_Donor_Fragment(), 1);
                } else {
                    LoadFragment(new ProfileFragment(), 1);
                }
                return true;
            }

        });

//        bn_view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                bn_view.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.red)));
//                bn_view.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.red)));
//                return true;
//            }
//        });

    }

    private void LoadFragment(Fragment fragment, int flag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (flag == 0) {
            fragmentTransaction.add(R.id.container, fragment);
        } else {
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }
}