package com.example.student_login.vardaan.Activities;



import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.R;
import com.example.student_login.vardaan.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.student_login.vardaan.ApiUtlis.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment implements RecyclerAdapter5.RecyclerListener{

    RecyclerView reView1;
    AddActivityService addActivityService;
    List<Activity> activityList;
    RecyclerAdapter5 adapter;


    View view;
    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_activities, container, false);
        reView1=view.findViewById(R.id.reView1);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        reView1.setLayoutManager(layoutManager);

        addActivityService= ApiUtlis.getActivityService();
        addActivityService= RetrofitClient.getClient(BASE_URL).create(AddActivityService.class);
        Call<ActivityData> call=addActivityService.getActivity();
        call.enqueue(new Callback<ActivityData>() {
            @Override
            public void onResponse(Call<ActivityData> call, Response<ActivityData> response) {
                List<Activity> data=response.body().getActivity();
                setAdapter(data);
            }

            @Override
            public void onFailure(Call<ActivityData> call, Throwable t) {

            }
        });
        return view;
    }

    private void setAdapter(List<Activity> data) {
        adapter=new RecyclerAdapter5(data,this);
        reView1.setAdapter(adapter);
    }

    @Override
    public void onRecyclerClick(Activity list) {
        Fragment fragment=new ActivityDetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("Activity",list);
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainLayout,fragment)
                .addToBackStack(ActivitiesFragment.class.getName())
                .commit();
    }
}
