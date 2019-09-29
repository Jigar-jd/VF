package com.example.student_login.vardaan.Volunteer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AddAPIService {
    @FormUrlEncoded
    @POST("include/volunteerInsert.php")
     Call<String> getStringScalar(@Field("fName") String fName,
                                  @Field("lName") String lName,
                                  @Field("email_id") String email_id,
                                  @Field("contact_number") String contact_number,
                                  @Field("city") String city,
                                  @Field("availbility") String availbility,
                                  @Field("gender") String gender,
                                  @Field("birth_date") String birth_date,
                                  @Field("blood_group") String blood_group,
                                  @Field("profession") String profession);
}
