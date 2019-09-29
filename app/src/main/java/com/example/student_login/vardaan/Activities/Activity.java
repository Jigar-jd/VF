package com.example.student_login.vardaan.Activities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Activity  implements Parcelable
{
  @SerializedName("a_id")
  private String a_id;

  protected Activity(Parcel in) {
    a_id = in.readString();
    a_title = in.readString();
    a_date = in.readString();
    a_place = in.readString();
    a_img_path = in.readString();
    a_description = in.readString();
  }

  public static final Creator<Activity> CREATOR = new Creator<Activity>() {
    @Override
    public Activity createFromParcel(Parcel in) {
      return new Activity(in);
    }

    @Override
    public Activity[] newArray(int size) {
      return new Activity[size];
    }
  };

  public String getAId() { return this.a_id; }

  public void setAId(String a_id) { this.a_id = a_id; }

  @SerializedName("a_title")
  private String a_title;

  public String getATitle() { return this.a_title; }

  public void setATitle(String a_title) { this.a_title = a_title; }

  @SerializedName("a_date")
  private String a_date;

  public String getADate() { return this.a_date; }

  public void setADate(String a_date) { this.a_date = a_date; }

  @SerializedName("a_place")
  private String a_place;

  public String getAPlace() { return this.a_place; }

  public void setAPlace(String a_place) { this.a_place = a_place; }

  @SerializedName("a_img_path")
  private String a_img_path;

  public String getAImgPath() { return this.a_img_path; }

  public void setAImgPath(String a_img_path) { this.a_img_path = a_img_path; }

  @SerializedName("a_description")
  private String a_description;

  public String getADescription() { return this.a_description; }

  public void setADescription(String a_description) { this.a_description = a_description; }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {

    dest.writeString(a_title);
    dest.writeInt(Integer.parseInt(a_id));
    dest.writeString(a_date);
    dest.writeString(a_place);
    dest.writeString(a_img_path);
    dest.writeString(a_description);

  }
}