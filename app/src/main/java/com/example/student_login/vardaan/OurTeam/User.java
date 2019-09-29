package com.example.student_login.vardaan.OurTeam;


import com.google.gson.annotations.SerializedName;

public class User
{
  @SerializedName("id")
  private String id;

  @SerializedName("name")
  private String name;

  @SerializedName("image_url")
  private String image_url;



  @SerializedName("designation")
  private String designation;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImage_url() {
    return image_url;
  }

  public void setImage_url(String image_url) {
    this.image_url = image_url;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String designation) {
    this.designation = designation;
  }
}