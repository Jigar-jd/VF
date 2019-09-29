package com.example.student_login.vardaan.Gallery;

import com.google.gson.annotations.SerializedName;

public class Gallery
{
  @SerializedName("g_id")
  private String g_id;

  public String getGId() { return this.g_id; }

  public void setGId(String g_id) { this.g_id = g_id; }

  @SerializedName("g_img_path")
  private String g_img_path;

  public String getGImgPath() { return this.g_img_path; }

  public void setGImgPath(String g_img_path) { this.g_img_path = g_img_path; }

  @SerializedName("g_img_category")
  private String g_img_category;

  public String getGImgCategory() { return this.g_img_category; }

  public void setGImgCategory(String g_img_category) { this.g_img_category = g_img_category; }

  @SerializedName("g_img_year")
  private String g_img_year;

  public String getGImgYear() { return this.g_img_year; }

  public void setGImgYear(String g_img_year) { this.g_img_year = g_img_year; }
}