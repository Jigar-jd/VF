package com.example.student_login.vardaan.OurTeam;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.Project.ProjectList;
import com.example.student_login.vardaan.Project.RecyclerAdapter;
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
public class OurTeamFragment extends Fragment {


    public OurTeamFragment() {
        // Required empty public constructor
    }
    RecyclerView teamReView;
    AddTeamService addTeamService;
    List<User> users;
    RecyclerAdapter1 adapter1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_our_team, container, false);
        // Inflate the layout for this fragment
        teamReView=view.findViewById(R.id.teamReView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        teamReView.setLayoutManager(layoutManager);

        addTeamService= ApiUtlis.getAddTeamService();
        addTeamService= RetrofitClient.getClient(BASE_URL).create(AddTeamService.class);
        Call<RootObject> call=addTeamService.getTeam();

        call.enqueue(new Callback<RootObject>() {
            @Override
            public void onResponse(Call<RootObject> call, Response<RootObject> response) {

                List<User> data = response.body().getUsers();
                adapter1=new RecyclerAdapter1(data,getContext());
                teamReView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<RootObject> call, Throwable t) {

            }
        });

        return view;
    }

}
