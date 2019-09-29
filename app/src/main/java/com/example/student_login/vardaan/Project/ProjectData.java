package com.example.student_login.vardaan.Project;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ProjectData {
    @SerializedName("project")
    ArrayList<ProjectList> projects;

    public ArrayList<ProjectList> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<ProjectList> projects) {
        this.projects = projects;
    }
}
