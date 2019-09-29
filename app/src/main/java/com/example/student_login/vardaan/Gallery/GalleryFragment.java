package com.example.student_login.vardaan.Gallery;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.student_login.vardaan.ApiUtlis;

import com.example.student_login.vardaan.R;
import com.example.student_login.vardaan.RetrofitClient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.student_login.vardaan.ApiUtlis.BASE_URL;
import static com.example.student_login.vardaan.ApiUtlis.getAddGalleryService;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {
    RecyclerView GalleryReView;
    AddGalleryService addGalleryService;
    RecyclerAdapter2 adapter2;
    Spinner YearSpinner,CategoriesSpinner;
    private ArrayList<String> yearList;
    private ArrayList<String> categorieslist;
    private ArrayList<Gallery> masterData, adapterData;
    Context context;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context=getActivity();
        View view=inflater.inflate(R.layout.fragment_gallery, container, false);
        GalleryReView=view.findViewById(R.id.GalleryReView);
        YearSpinner=view.findViewById(R.id.YearSpinner);
        CategoriesSpinner=view.findViewById(R.id.CategoriesSpinner);

        GalleryReView.setLayoutManager(new GridLayoutManager(getContext(),3));

        addGalleryService= ApiUtlis.getAddGalleryService();
        addGalleryService= RetrofitClient.getClient(BASE_URL).create(AddGalleryService.class);
        Call<RootGallery> call=addGalleryService.getAddGalleryService();
        call.enqueue(new Callback<RootGallery>() {
            @Override
            public void onResponse(Call<RootGallery> call, Response<RootGallery> response) {
                masterData = response.body().getGallery();
                adapterData=masterData;
                HashSet<String> year=new HashSet<>();
                HashSet<String> categories=new HashSet<>();

                for (Gallery g:masterData){
                    year.add(g.getGImgYear());
                }
                for (Gallery g:masterData){
                    categories.add(g.getGImgCategory());
                }
                yearList=new ArrayList<>(year);
                categorieslist=new ArrayList<>(categories);
                yearList.add(0,"");
                categorieslist.add(0,"");
                adapter2=new RecyclerAdapter2(adapterData,context);
                GalleryReView.setAdapter(adapter2);
                ArrayAdapter<String> adapter3=
                        new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,yearList);
                ArrayAdapter<String> adapter4=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,categorieslist);
                YearSpinner.setAdapter(adapter3);
                CategoriesSpinner.setAdapter(adapter4);

            }

            @Override
            public void onFailure(Call<RootGallery> call, Throwable t) {

            }
        });

       /* ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                getAddGalleryService(),
                android.R.layout.simple_spinner_dropdown_item);*/


        YearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    adapterData=masterData;

                }else {
                    String year = yearList.get(position);
                    adapterData = new ArrayList<>();
                    for (Gallery gallery : masterData) {
                        if (year.equals(gallery.getGImgYear())) {
                            adapterData.add(gallery);
                        }
                    }
                }
                adapter2 = new RecyclerAdapter2(adapterData, context);
                GalleryReView.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CategoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

}
