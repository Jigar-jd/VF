package com.example.student_login.vardaan.Project;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.Founders.DarshanFragment;
import com.example.student_login.vardaan.R;
import com.example.student_login.vardaan.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.student_login.vardaan.ApiUtlis.BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class Project extends Fragment implements RecyclerAdapter.RecyclerListener {


    public Project() {
        // Required empty public constructor
    }
    RecyclerView reView;
    AddProjectService addProjectService;
    List<ProjectList> projectLists;
    RecyclerAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_project, container, false);
        // Inflate the layout for this fragment
        reView=view.findViewById(R.id.reView);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        reView.setLayoutManager(layoutManager);



        addProjectService= ApiUtlis.getAddProjectService();


        addProjectService= RetrofitClient.getClient(BASE_URL).create(AddProjectService.class);
        Call<ProjectData> call=addProjectService.getProjects();

        call.enqueue(new Callback<ProjectData>() {
            @Override
            public void onResponse(Call<ProjectData> call, Response<ProjectData> response) {
                List<ProjectList> data = response.body().getProjects();
                setAdapter(data);
            }

            @Override
            public void onFailure(Call<ProjectData> call, Throwable t) {

            }
        });


        return view;
    }

    private void setAdapter(List<ProjectList> data) {
        adapter=new RecyclerAdapter(data,this);
        reView.setAdapter(adapter);
    }

    @Override
    public void onRecyclerClick(ProjectList list) {
        Fragment fragment=new ProjectDetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("PROJECT",list);
        fragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.MainLayout,fragment)
                .addToBackStack(Project.class.getName())
                .commit();
    }
}
