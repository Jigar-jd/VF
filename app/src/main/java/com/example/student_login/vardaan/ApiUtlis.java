package com.example.student_login.vardaan;

import com.example.student_login.vardaan.Activities.AddActivityService;
import com.example.student_login.vardaan.Gallery.AddGalleryService;
import com.example.student_login.vardaan.SignUp.APISignUpService;
import com.example.student_login.vardaan.Upcoming.AddUpcomingService;
import com.example.student_login.vardaan.Contact.APIService;
import com.example.student_login.vardaan.Login.UserServies;
import com.example.student_login.vardaan.OurTeam.AddTeamService;
import com.example.student_login.vardaan.Project.AddProjectService;
import com.example.student_login.vardaan.Volunteer.AddAPIService;

public class ApiUtlis {
    private ApiUtlis(){}
    public  static final String BASE_URL="http://jdpatelworld.000webhostapp.com/";
    //10.0.2.2
    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
    public static APISignUpService getAPISignUpService(){
        return RetrofitClient.getClient(BASE_URL).create(APISignUpService.class);
    }
    public static AddAPIService getAddAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(AddAPIService.class);
    }
    public static AddProjectService getAddProjectService(){
        return RetrofitClient.getClient(BASE_URL).create(AddProjectService.class);
    }
    public static AddTeamService getAddTeamService(){
        return RetrofitClient.getClient(BASE_URL).create(AddTeamService.class);
    }
    public static AddUpcomingService getAddUpcomingService(){
        return RetrofitClient.getClient(BASE_URL).create(AddUpcomingService.class);
    }
    public static UserServies getUserServies(){
        return RetrofitClient.getClient(BASE_URL).create(UserServies.class);
    }
    public static AddGalleryService getAddGalleryService(){
        return RetrofitClient.getClient(BASE_URL).create(AddGalleryService.class);
    }
    public static AddActivityService getActivityService(){
        return RetrofitClient.getClient(BASE_URL).create(AddActivityService.class);
    }



}
