package com.example.student_login.vardaan.SignUp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APISignUpService {
    @FormUrlEncoded
    @POST("include/signup.php")
    Call<String> getStringScalar(@Field("username") String username,
                                 @Field("password") String password,
                                 @Field("mobile") String mobile,
                                 @Field("email") String email,
                                 @Field("gender") String gender);
}