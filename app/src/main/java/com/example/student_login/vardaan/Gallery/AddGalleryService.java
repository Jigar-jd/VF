package com.example.student_login.vardaan.Gallery;

import com.example.student_login.vardaan.OurTeam.RootObject;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AddGalleryService {
    @POST("include/SelectGallery.php")
    Call<RootGallery> getAddGalleryService();
}
