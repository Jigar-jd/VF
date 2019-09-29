package com.example.student_login.vardaan.OurTeam;

import com.example.student_login.vardaan.Project.ProjectData;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AddTeamService {
    @POST("include/teamSelect.php")
    Call<RootObject> getTeam();
}
