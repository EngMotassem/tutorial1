package com.example.tutorial1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial1.R;
import com.example.tutorial1.models.UserModel;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userViewHolder>{

    private Context context;
    private List<UserModel> users;

    public UserAdapter(Context context, List<UserModel> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(context).inflate(R.layout.user,parent,false);

        return new UserAdapter.userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {

        UserModel userModel;
        userModel=users.get(position);

        holder.username.setText(userModel.getUsername());


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class userViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView userImage;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);

            username=itemView.findViewById(R.id.user_name);
            userImage=itemView.findViewById(R.id.profile_image);
        }
    }
}
