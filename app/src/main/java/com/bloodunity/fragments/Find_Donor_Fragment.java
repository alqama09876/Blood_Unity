package com.bloodunity.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.bloodunity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Find_Donor_Fragment extends Fragment {

    EditText edt_location;
    AppCompatButton btn_search;
    Spinner spn_gender;
    FirebaseFirestore db;

    public Find_Donor_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find__donor_, container, false);

//        find ids...
        edt_location = view.findViewById(R.id.edt_location);
        btn_search = view.findViewById(R.id.btn_search);
        spn_gender = view.findViewById(R.id.spn_gender);
        db = FirebaseFirestore.getInstance();


        // GENDER SPINNER WORK...
        // ----------------------

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.bloodtype_options, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spn_gender.setAdapter(adapter);


//        search btn work...
        btn_search.setOnClickListener(new View.OnClickListener() { // search k button pr click kerte hi firebase se tamam users dikhane hai...
            @Override
            public void onClick(View v) {
                String location = edt_location.getText().toString().trim();

                if (TextUtils.isEmpty(location)) {
                    edt_location.setError("Required*");
                    Toast.makeText(getContext(), "Fill all the fields", Toast.LENGTH_SHORT).show();
                }

//                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.container, new ExploreDonorFragment());
//                transaction.addToBackStack(null); // Optionally, add the fragment to the back stack
//                transaction.commit();

                else {
                    Query query = db.collection("users")
                            .whereEqualTo("Location", location);

                    query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                    documentSnapshot.getData();

                                }
                            } else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return view;
    }


}