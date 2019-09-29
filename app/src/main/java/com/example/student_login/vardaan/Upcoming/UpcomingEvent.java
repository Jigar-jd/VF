package com.example.student_login.vardaan.Upcoming;

import com.google.gson.annotations.SerializedName;

public class UpcomingEvent
{
  @SerializedName("evnt_id")
  private String evnt_id;

  public String getEvntId() { return this.evnt_id; }

  public void setEvntId(String evnt_id) { this.evnt_id = evnt_id; }

  @SerializedName("evnt_name")
  private String evnt_name;

  public String getEvntName() { return this.evnt_name; }

  public void setEvntName(String evnt_name) { this.evnt_name = evnt_name; }

  @SerializedName("event_date")
  private String event_date;

  public String getEventDate() { return this.event_date; }

  public void setEventDate(String event_date) { this.event_date = event_date; }

  @SerializedName("event_location")
  private String event_location;

  public String getEventLocation() { return this.event_location; }

  public void setEventLocation(String event_location) { this.event_location = event_location; }
}