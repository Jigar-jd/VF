package com.example.student_login.vardaan.About;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.student_login.vardaan.Upcoming.AddUpcomingService;
import com.example.student_login.vardaan.Upcoming.RecyclerEventAdapter;
import com.example.student_login.vardaan.Upcoming.UpcomingEvent;
import com.example.student_login.vardaan.Upcoming.upconingEventData;
import com.example.student_login.vardaan.slider.ViewPageAdapter;
import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.R;
import com.example.student_login.vardaan.RetrofitClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.student_login.vardaan.ApiUtlis.BASE_URL;


public class HomeFragment extends Fragment {

    ViewPager viewPager;
    RecyclerView UpcomingRecycle;
    LinearLayout sliderDots;
    public int dotCounts;
    public ImageView[] dots;
    AddUpcomingService addUpcomingService;
    List<UpcomingEvent> upcomingEvents;
    RecyclerEventAdapter recyclerEventAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        viewPager=view.findViewById(R.id.viewPager);
        sliderDots=view.findViewById(R.id.sliderDots);

        UpcomingRecycle=view.findViewById(R.id.UpcomingRecycle);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        UpcomingRecycle.setLayoutManager(layoutManager);

        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getContext());
        viewPager.setAdapter(viewPageAdapter);

        dotCounts=viewPageAdapter.getCount();
        dots=new ImageView[dotCounts];
        for(int i=0;i<dotCounts;i++){
            dots[i]=new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.nonactive_dots));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dots));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotCounts; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.nonactive_dots));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dots));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTimerTask(), 4000 ,4000);*/
        addUpcomingService= ApiUtlis.getAddUpcomingService();
        addUpcomingService= RetrofitClient.getClient(BASE_URL).create(AddUpcomingService.class);
        Call<upconingEventData> call=addUpcomingService.getEvent();
        call.enqueue(new Callback<upconingEventData>() {
            @Override
            public void onResponse(Call<upconingEventData> call, Response<upconingEventData> response) {
                List<UpcomingEvent> data = response.body().getUpcomingEvent();
                recyclerEventAdapter=new RecyclerEventAdapter(data,getContext());
                UpcomingRecycle.setAdapter(recyclerEventAdapter);
            }

            @Override
            public void onFailure(Call<upconingEventData> call, Throwable t) {

            }
        });
        return view;
    }


    /*private class myTimerTask extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });
        }
    }*/
}





