package com.example.artgram.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel

public class RecentPhotos {

    @SerializedName("id")
    protected String id;
    @SerializedName("created_at")
    protected String createdAt;
    @SerializedName("updated_at")
    protected String updatedAt;
    @SerializedName("color")
    protected String color;
    @SerializedName("description")
    protected String description;
    @SerializedName("urls")
    protected PhotoUrl url=new PhotoUrl();
    @SerializedName("user")
    protected User user=new User();

    public RecentPhotos(){

    }

    public RecentPhotos(String id, String createdAt, String updatedAt, String color, String description, PhotoUrl url, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.color = color;
        this.description = description;
        this.url = url;
        this.user = user;
    }

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
