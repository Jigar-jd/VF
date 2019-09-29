package com.example.student_login.vardaan.SignUp;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;

import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.Login.LoginActivity;
import com.example.student_login.vardaan.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



/**

 * A simple {@link Fragment} subclass.
 */

public class SignUpFragment extends Fragment {

    View view;
    TextView SignaIntxt;
    EditText etxtUser,etxtEmail,etxtPassword,etxtMobile;
    RadioGroup RgGendergroup;
    RadioButton rbMale,rbFemale;
    Button SignUpbtn;
    Context context;
    private String UserGender,user,email,password,mobile;

    private APISignUpService vAPISignUpService;
    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        etxtUser=view.findViewById(R.id.etxtUser);
        etxtEmail=view.findViewById(R.id.etxtEmail);
        etxtPassword=view.findViewById(R.id.etxtPassword);
        etxtMobile=view.findViewById(R.id.etxtMobile);
        this.context=context;

        RgGendergroup=view.findViewById(R.id.RgGendergroup);
        rbMale=view.findViewById(R.id.rbMale);
        rbFemale=view.findViewById(R.id.rbFemale);

        SignUpbtn=view.findViewById(R.id.SignUpbtn);
        SignaIntxt=view.findViewById(R.id.SignaIntxt);
        vAPISignUpService= ApiUtlis.getAPISignUpService();

        RgGendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMale:
                        UserGender="Male";

                        break;
                    case R.id.rbFemale:
                        UserGender="Female";
                        break;
                }
            }
        });
        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                etxtUser.setText("");
                etxtEmail.setText("");
                etxtPassword.setText("");
                etxtMobile.setText("");
            }
        });
        String text = "If you have an accont:- Sign In";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Sign Up page", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
               

            }

            @SuppressLint("ResourceType")
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(255, 26, 117));
                ds.setUnderlineText(false);
                ds.setFakeBoldText(true);

            }
        };
        ss.setSpan(clickableSpan, 24, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        SignaIntxt.setText(ss);
        SignaIntxt.setMovementMethod(LinkMovementMethod.getInstance());




        return view;
    }

    private void submit() {
        initialize();
        if(!validate()){
            Toast.makeText(getContext(),"Submit is not successfull",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            onSubmitSuccess();
            Intent intent=new Intent(getContext(), LoginActivity.class);
            startActivity(intent);

        }
    }

    private void onSubmitSuccess() {
        sendPost(etxtUser.getText().toString(),etxtPassword.getText().toString(),etxtMobile.getText().toString(),etxtEmail.getText().toString(),UserGender);
        
    }


    private void sendPost(String etxtUser, String etxtPassword, String etxtMobile, String etxtEmail, String userGender) {
        Call<String> call=vAPISignUpService.getStringScalar(etxtUser,etxtPassword,etxtMobile,etxtEmail,userGender);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(getContext(), response.body(), Toast.LENGTH_LONG).show();
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
        if(user.isEmpty()||user.length()>32){
            etxtUser.setError("Please Enter Valid User Name");
            valid=false;
        }
        if(password.isEmpty()||password.length()>32){
            etxtPassword.setError("Please Enter Valid Password");
            valid=false;
        }
        if(mobile.isEmpty()||mobile.length()<10){
            etxtMobile.setError("Please Enter Valid Mobile Number");
            valid=false;
        }
        if(email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etxtEmail.setError("Please Enter Valid Email Address");
            valid=false;
        }
        return valid;
    }

    private void initialize() {
        user=etxtUser.getText().toString().trim();
        password=etxtPassword.getText().toString().trim();
        mobile=etxtMobile.getText().toString().trim();
        email=etxtEmail.getText().toString().trim();
    }

}
