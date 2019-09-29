package com.example.student_login.vardaan.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserServies {

    @FormUrlEncoded
    @POST("include/login.php")
    Call<String> login(@Field("username") String username, @Field("password") String password );

}
