package com.example.student_login.vardaan.Activities;

import retrofit2.http.POST;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AddActivityService {
    @POST("include/SelectActivity.php")
    Call<ActivityData> getActivity();
}