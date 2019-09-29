package com.example.student_login.vardaan.Activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.student_login.vardaan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityDetailsFragment extends Fragment {


    public ActivityDetailsFragment() {
        // Required empty public constructor
    }

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity_details, container, false);
        // Inflate the layout for this fragment
        TextView tvId,tvTitle,tvDate,tvPlace,tvDesc;
        ImageView tvImg;
        context=getActivity();
        Bundle bundle=getArguments();
        Activity list=bundle.getParcelable("Activity");

        tvId=view.findViewById(R.id.tvID);
        tvTitle=view.findViewById(R.id.tvTitle);
        tvDate=view.findViewById(R.id.tvDate);
        tvPlace=view.findViewById(R.id.tvPlace);
        tvImg=view.findViewById(R.id.tvImg);
        tvDesc=view.findViewById(R.id.tvDesc);

        tvId.setText(list.getAId()+"");
        tvTitle.setText(list.getATitle());
        tvDate.setText(list.getADate()+"");
        tvPlace.setText(list.getAPlace());
        Glide.with(context)
                .load("https://jdpatelworld.000webhostapp.com/include"+list.getAImgPath())
                .into(tvImg);
        //String msg = list.getADescription().replaceAll("</br>", "<br />");
        tvDesc.setText(Html.fromHtml(list.getADescription()),null);

        return view;

    }

}
