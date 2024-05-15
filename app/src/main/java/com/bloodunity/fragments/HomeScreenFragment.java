package com.bloodunity.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bloodunity.R;
import com.bloodunity.adapters.UserAdapter;
import com.bloodunity.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HomeScreenFragment extends Fragment {

    //    RecyclerView rv_users;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CardView card_view;

//    CircleImageView iv_image;
//    ProgressDialog progressDialog;

    public HomeScreenFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);

        //find ids...
//        rv_users = view.findViewById(R.id.rv_users); //
        card_view = view.findViewById(R.id.card_view);

        // set Layout Manager (How the layout looks)...
//        rv_users.setLayoutManager(new LinearLayoutManager(getContext())); //

        // set Adapter...
        // userAdapter = new UserAdapter(getActivity(), userArrayList); //
        // rv_users.setAdapter(userAdapter); //


        // custom function --> for get users data from firebase...
        fetchUserData();


        // custom function --> for get users data from firebase...
//        EventChangeListener(); //


        // onClick on image and open new activity...
//        card_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), UserInfoActivity.class));
//            }
//        });

        return view;
    }

    private void fetchUserData() {
        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<UserModel> userArrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshots : task.getResult()) {
                        UserModel userModel = documentSnapshots.toObject(UserModel.class);
                        userArrayList.add(userModel);

                        RecyclerView rv_users = getView().findViewById(R.id.rv_users);
                        UserAdapter userAdapter = new UserAdapter(getContext(), userArrayList);
                        rv_users.setAdapter(userAdapter);
                        rv_users.setLayoutManager(new LinearLayoutManager(getContext()));
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


//    private void EventChangeListener() {
//        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (error != null) {
//                    if (progressDialog.isShowing())
//                        progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Firestore Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                for (DocumentChange dc : value.getDocumentChanges()) {
//                    if (dc.getType() == DocumentChange.Type.ADDED) {
//                        userArrayList.add(dc.getDocument().toObject(UserModel.class));
//                    }
//                    userAdapter.notifyDataSetChanged();
//                    if (progressDialog.isShowing())
//                        progressDialog.dismiss();
//                }
//            }
//        });
//    }

}


// DELETE USER...
// --------------

// Home fragment pr user apni info ko delete kersakta hai jese wo slide kerke delete kersakta hai ya long click listener kerke delete
// kersakta but mujhe ye kerne hai k wo user app se to delete ho or firebase se bhi delete ho...


//db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
//        @SuppressLint("NotifyDataSetChanged")
//        @Override
//        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//            if (error != null) {
//                if (progressDialog.isShowing())
//                    progressDialog.dismiss();
//                Toast.makeText(getActivity(), "Firestore Error " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            for (DocumentChange dc : value.getDocumentChanges()) {
//                if (dc.getType() == DocumentChange.Type.ADDED) {
//                    userArrayList.add(dc.getDocument().toObject(UserModel.class));
//                }
//                userAdapter.notifyDataSetChanged();
//                if (progressDialog.isShowing())
//                    progressDialog.dismiss();
//            }
//        }
//    });
//}