package com.example.student_login.vardaan.Upcoming;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AddUpcomingService {
    @POST("include/SelectUpcoming.php")
    Call<upconingEventData> getEvent();
}
