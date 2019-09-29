package com.example.student_login.vardaan.Project;

        import android.os.Parcel;
        import android.os.Parcelable;

        import com.google.gson.annotations.SerializedName;

public class ProjectList implements Parcelable{
    @SerializedName("p_title")
    String Title;

    @SerializedName("p_id")
    int ID;

    @SerializedName("p_date")
    String Date;

    @SerializedName("p_place")
    String place;

    @SerializedName("p_coordinator")
    String coordinator;

    @SerializedName("p_discription")
    String description;

    protected ProjectList(Parcel in) {
        Title = in.readString();
        ID = in.readInt();
        Date = in.readString();
        place = in.readString();
        coordinator = in.readString();
        description=in.readString();
    }

    public static final Creator<ProjectList> CREATOR = new Creator<ProjectList>() {
        @Override
        public ProjectList createFromParcel(Parcel in) {
            return new ProjectList(in);
        }

        @Override
        public ProjectList[] newArray(int size) {
            return new ProjectList[size];
        }
    };

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeInt(ID);
        dest.writeString(Date);
        dest.writeString(place);
        dest.writeString(coordinator);
        dest.writeString(description);
    }

    @Override
    public String toString() {
        return String.valueOf(0);
    }
}
