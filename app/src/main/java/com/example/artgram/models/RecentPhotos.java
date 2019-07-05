package com.example.artgram.models;

import com.google.gson.annotations.SerializedName;

//import org.parceler.Parcel;
//
//@Parcel

public class RecentPhotos {

    @SerializedName("id")
    private String id;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("color")
    private String color;
    @SerializedName("description")
    private String description;
    @SerializedName("urls")
    private PhotoUrl url=new PhotoUrl();
    @SerializedName("user")
    private User user=new User();

//    public RecentPhotos(String id, String createdAt, String updatedAt, String color, String description, PhotoUrl url, User user) {
//        this.id = id;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//        this.color = color;
//        this.description = description;
//        this.url = url;
//        this.user = user;
//    }

    public String getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public PhotoUrl getUrl() {
        return url;
    }

    public User getUser() {
        return user;
    }
}
