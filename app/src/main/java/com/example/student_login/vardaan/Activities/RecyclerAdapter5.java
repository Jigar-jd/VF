package com.example.student_login.vardaan.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student_login.vardaan.Project.RecyclerAdapter;
import com.example.student_login.vardaan.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter5 extends RecyclerView.Adapter<RecyclerAdapter5.MyViewHolder5> {
    List<Activity> activities;
    Context context;

    ArrayList<Activity> data;
    public interface RecyclerListener{
        void onRecyclerClick(Activity list);
    }
    RecyclerListener listener;
    public RecyclerAdapter5(List<Activity> activities,RecyclerListener listener) {
        this.activities=activities;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public RecyclerAdapter5.MyViewHolder5 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_activities,parent,false);
        RecyclerAdapter5.MyViewHolder5 myViewHolder5=new MyViewHolder5(view);

        return myViewHolder5;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter5.MyViewHolder5 holder, int position) {
            final Activity activity=activities.get(position);
        holder.tvTitle.setText(activities.get(position).getATitle());
        holder.tvID.setText(activities.get(position).getAId()+"");
        holder.tvDate.setText(activities.get(position).getADate());
        holder.tvPlace.setText(activities.get(position).getAPlace());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerClick(activity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public class MyViewHolder5 extends RecyclerView.ViewHolder {
        TextView tvTitle, tvID, tvDate, tvPlace;
        public MyViewHolder5(View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPlace = itemView.findViewById(R.id.tvPlace);
        }
    }
}