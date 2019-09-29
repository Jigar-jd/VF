package com.example.student_login.vardaan.Project;



import retrofit2.Call;

import retrofit2.http.POST;

public interface AddProjectService {
    @POST("include/SelectProject.php")
    Call<ProjectData> getProjects();
}
