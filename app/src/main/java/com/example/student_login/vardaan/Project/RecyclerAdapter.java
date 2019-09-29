package com.example.student_login.vardaan.Project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student_login.vardaan.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<ProjectList> projectLists;
    private Context context;

    ArrayList<ProjectList>data;
    public interface RecyclerListener{
        void onRecyclerClick(ProjectList list);
    }
    RecyclerListener listener;
    public RecyclerAdapter(List<ProjectList> projectLists,RecyclerListener listener) {
        this.projectLists=projectLists;
        this.context = context;
        this.listener=listener;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_activity,parent,false);
     MyViewHolder myViewHolder=new MyViewHolder(view);

     return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final ProjectList projectList=projectLists.get(position);
        holder.tvTitle.setText(projectLists.get(position).getTitle());
        holder.tvID.setText(projectLists.get(position).getID()+"");
        holder.tvDate.setText(projectLists.get(position).getDate());
        holder.tvPlace.setText(projectLists.get(position).getPlace());
        holder.tvCoordinator.setText(projectLists.get(position).getCoordinator());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerClick(projectList);
            }
        });
    }


    @Override
    public int getItemCount() {
        return projectLists.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvID, tvDate, tvPlace, tvCoordinator;



        public MyViewHolder(View itemView) {
            super(itemView);
            tvCoordinator = itemView.findViewById(R.id.tvCoordinator);
            tvID = itemView.findViewById(R.id.tvID);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvPlace = itemView.findViewById(R.id.tvPlace);



        }




    }
}
