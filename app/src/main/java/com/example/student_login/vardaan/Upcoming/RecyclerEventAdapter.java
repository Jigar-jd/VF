package com.example.student_login.vardaan.Upcoming;

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

public class RecyclerEventAdapter  extends RecyclerView.Adapter<RecyclerEventAdapter.MyViewHolder2> {
    private List<UpcomingEvent> upcomingEvents;
    private Context context;
    ArrayList<UpcomingEvent> data3;

    public RecyclerEventAdapter(List<UpcomingEvent> upcomingEvents, Context context) {
        this.upcomingEvents = upcomingEvents;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerEventAdapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_upcoming,parent,false);
        MyViewHolder2 myViewHolder2=new MyViewHolder2(view);
        return myViewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerEventAdapter.MyViewHolder2 holder, int position) {
            holder.upcomingTitle.setText(upcomingEvents.get(position).getEvntName());
            holder.EventNo.setText(upcomingEvents.get(position).getEvntId());
            holder.upcomingDate.setText(upcomingEvents.get(position).getEventDate());
            holder.upcomingLocation.setText(upcomingEvents.get(position).getEventLocation());
    }

    @Override
    public int getItemCount() {
        return upcomingEvents.size();
    }


    public class MyViewHolder2 extends RecyclerView.ViewHolder {
        TextView upcomingTitle,EventNo,upcomingDate,upcomingLocation;
        public MyViewHolder2(View itemView) {
            super(itemView);
            upcomingTitle=itemView.findViewById(R.id.upcomingTitle);
            EventNo=itemView.findViewById(R.id.EventNo);
            upcomingDate=itemView.findViewById(R.id.upcomingDate);
            upcomingLocation=itemView.findViewById(R.id.upcomingLocation);
        }
    }
}
