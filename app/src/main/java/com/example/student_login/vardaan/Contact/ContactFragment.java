package com.example.student_login.vardaan.Contact;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.student_login.vardaan.R;
import com.example.student_login.vardaan.ApiUtlis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ContactFragment extends Fragment {
    View view;
    EditText cName,cSubject,cEmail,CMessage;
    String Cname,Csubject,Cemail,Cmeesage;
    Button btnSubmit;
   private APIService mAPIService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_contact, container, false);

        cName=view.findViewById(R.id.ContactName);
        cSubject=view.findViewById(R.id.contactSubject);
        cEmail=view.findViewById(R.id.ContactMail);
        CMessage=view.findViewById(R.id.contactMessage);

       mAPIService = ApiUtlis.getAPIService();

        btnSubmit=view.findViewById(R.id.ButtonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        return view;
    }

    private void submit() {
        initialize();
        if(!validate()){
            Toast.makeText(getContext(),"Submit is not successfull",Toast.LENGTH_LONG).show();
        }
        else{
            onSubmitSuccess();
        }
    }

    private void onSubmitSuccess() {
        sendPost(cName.getText().toString(), cEmail.getText().toString(),cSubject.getText().toString(),  CMessage.getText().toString());


    }

    private void sendPost(String name, String email, String subject, String message) {
        Call<String> call=mAPIService.getStringScalar(name,email,subject,message);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Response",response.body());
                Toast.makeText(getContext(),"DATA INSERTED",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("EX",t.toString());
                Toast.makeText(getContext(),"ERROR",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validate() {
        boolean valid=true;
        if(Cname.isEmpty()||Cname.length()>32){
            cName.setError("Please Enter Valid Name");
            valid=false;
        }
        if(Csubject.isEmpty()||Csubject.length()>32){
            cSubject.setError("Please Enter Valid Subject Title");
            valid=false;
        }
        if(Cmeesage.isEmpty()||Cmeesage.length()>32){
            CMessage.setError("Please Enter Message");
            valid=false;
        }
        if(Cemail.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(Cemail).matches()){
            cEmail.setError("Please Enter Valid Email Address");
            valid=false;
        }



        return valid;
    }
    private void initialize() {
        Cname=cName.getText().toString().trim();
        Csubject=cSubject.getText().toString().trim();
        Cmeesage=CMessage.getText().toString().trim();
        Cemail=cEmail.getText().toString().trim();


    }
}
