package com.example.student_login.vardaan.OurTeam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.student_login.vardaan.Gallery.Gallery;
import com.example.student_login.vardaan.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.MyViewHolder1> {

    private List<User> users;
    private Context context;
   // ArrayList<User> data;

    public RecyclerAdapter1(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter1.MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.our_raw_team,parent,false);
        MyViewHolder1 myViewHolder1=new MyViewHolder1(view);
        return myViewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter1.MyViewHolder1 holder, int position) {
        holder.TeamName.setText(users.get(position).getName());
        holder.TeamDesignation.setText(users.get(position).getDesignation());
        Glide.with(context)
                .load("http://jdpatelworld.000webhostapp.com/"+users.get(position).getImage_url())
                .into(holder.picTeam);


    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView TeamName,TeamDesignation;
        ImageView picTeam;

        public MyViewHolder1(View itemView) {
            super(itemView);
            TeamName=itemView.findViewById(R.id.TeamName);
            picTeam=itemView.findViewById(R.id.picTeam);
            TeamDesignation=itemView.findViewById(R.id.TeamDesignation);
        }
    }
}
