package com.example.student_login.vardaan.Login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.student_login.vardaan.ApiUtlis;
import com.example.student_login.vardaan.MainActivity;
import com.example.student_login.vardaan.R;
import com.example.student_login.vardaan.SignUp.SignUpActivity;
import com.example.student_login.vardaan.SignUp.SignUpFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {
    public static final String PREF_FILE = "My Pref File";
    public static final String USERNAME = "username";
    public static final String USERNAME1 = "username1";
    public static final String PASSWORD = "password";

    EditText etxtUser,etxtPassword;
    TextView signupTxt;
    Button SignInbtn;
    UserServies userServies;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        etxtUser = findViewById(R.id.etxtUser);
        etxtPassword = findViewById(R.id.etxtPassword);
        SignInbtn = findViewById(R.id.SignInbtn);
        signupTxt = findViewById(R.id.signupTxt);

        String text = "IF YOU DO NOT HAVE YOUR VARDAAN ACCOUNT :- SIGN UP";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Sign Up page", Toast.LENGTH_SHORT).show();
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
        ss.setSpan(clickableSpan, 43, 50, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        signupTxt.setText(ss);
        signupTxt.setMovementMethod(LinkMovementMethod.getInstance());

        userServies = ApiUtlis.getUserServies();

        checkLogin();
        signupTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SignInbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username = etxtUser.getText().toString();
                String password = etxtPassword.getText().toString();

                if (validateLogin(username, password)) {
                    doLogin(username, password);

                }
            }
        });
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.student_login.vardaan.Login",PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    private void checkLogin() {
            SharedPreferences sharedPref=getSharedPreferences(PREF_FILE,MODE_PRIVATE);
            String username=sharedPref.getString(USERNAME,null);
            if(username!=null){
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }

    }

    private void doLogin(final String username, final String password) {
        Call<String> call = userServies.login(username, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                if (response.isSuccessful()) {
                    String resObj = response.body();
                    if (resObj.equals("success")) {
                        SharedPreferences sharedPref=getSharedPreferences(PREF_FILE,MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPref.edit();
                        editor.putString(USERNAME,username);
                        editor.putString(PASSWORD,password);
                        editor.commit();

                        Intent intent=new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                    }
                } else {
                        Toast.makeText(context, "UserNAme or Password is not valid", Toast.LENGTH_SHORT).show();
                }
              }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("Error",t.toString());
            }
        });
    }
        private boolean validateLogin(String username, String password ){
            if (username==null||username.trim().length()==0){
                Toast.makeText(this, "User name is required", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (password==null||password.trim().length()==0){
                Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
        public String getUserName(){
            SharedPreferences sharedPref=getSharedPreferences(PREF_FILE,MODE_PRIVATE);
            return sharedPref.getString(USERNAME,null);

        }
}
