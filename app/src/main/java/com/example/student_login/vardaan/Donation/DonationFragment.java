package com.example.student_login.vardaan.Donation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.student_login.vardaan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonationFragment extends Fragment {


    public DonationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_donation, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}
