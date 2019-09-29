package com.example.student_login.vardaan.Contact;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("include/ContactInsert.php")
    Call<String> getStringScalar(@Field("name") String name,@Field("email") String email,@Field("subject") String subject,@Field("message") String message);
}
