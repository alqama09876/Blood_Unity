package com.bloodunity.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bloodunity.R;
import com.bloodunity.activity.UserInfoActivity;
import com.bloodunity.models.UserModel;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    ArrayList<UserModel> userArrayList = new ArrayList<>();
    FirebaseFirestore db;
    FirebaseAuth auth;

    public UserAdapter(Context context, ArrayList<UserModel> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_items_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserModel userModel = userArrayList.get(position);

        holder.tv_name.setText(userModel.getName());
        holder.tv_phone.setText(userModel.getPhone());
        holder.tv_city.setText(userModel.getCity());
        holder.tv_blood_type.setText(userModel.getBloodType());
        holder.tv_location.setText(userModel.getLocation());
        Glide.with(holder.iv_image.getContext()).load(userModel.getProfile_image()).into(holder.iv_image);

        // on click on image and open new Activity...
//        holder.iv_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = ((FragmentActivity) v.getContext()).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, new UserDetailsFragment());
//                fragmentTransaction.commit();
//
//            }
//        });

        holder.iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserInfoActivity.class);
                context.startActivity(intent);
            }
        });


        holder.card_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                db.collection("users").document(auth.getCurrentUser().getUid()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            userArrayList.remove(userArrayList.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView iv_image;
        TextView tv_name, tv_phone, tv_city, tv_blood_type, tv_location;
        CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image = itemView.findViewById(R.id.iv_image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
            tv_city = itemView.findViewById(R.id.tv_city);
            tv_blood_type = itemView.findViewById(R.id.tv_blood_type);
            tv_location = itemView.findViewById(R.id.tv_location);
            card_view = itemView.findViewById(R.id.card_view);
        }
    }
}
