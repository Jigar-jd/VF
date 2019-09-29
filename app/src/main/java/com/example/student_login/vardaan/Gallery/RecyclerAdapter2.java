package com.example.student_login.vardaan.Gallery;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.example.student_login.vardaan.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.MyViewHolder2> {

    private List<Gallery> galleries;
    private Context context;
    ArrayList<Gallery> data2;

    public RecyclerAdapter2(List<Gallery> galleries,Context context) {
        this.context=context;
        this.galleries=galleries;
    }

    @NonNull
    @Override
    public RecyclerAdapter2.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_thumbnail,parent,false);
        RecyclerAdapter2.MyViewHolder2 myViewHolder2=new MyViewHolder2(view);
        return myViewHolder2;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter2.MyViewHolder2 holder, int position) {
        Glide.with(context)
                .load("http://jdpatelworld.000webhostapp.com/include/"+galleries.get(position).getGImgPath())
                .into(holder.img_gallery);

    }

    @Override
    public int getItemCount() {
        return galleries.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        ImageView img_gallery;
        public MyViewHolder2(View itemView) {
            super(itemView);
            img_gallery=itemView.findViewById(R.id.img_gallery);
        }
    }
}
