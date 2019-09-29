package com.example.student_login.vardaan.OurTeam;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RootObject
{
  @SerializedName("users")
  private ArrayList<User> users;

  public ArrayList<User> getUsers() { return this.users; }

  public void setUsers(ArrayList<User> users) { this.users = users; }
}
