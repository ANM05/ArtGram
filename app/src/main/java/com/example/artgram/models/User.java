package com.example.artgram.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class User {
    @SerializedName("id")
    protected String id;
    @SerializedName("username")
    protected String username;
    @SerializedName("bio")
    protected String bio;
    @SerializedName("total_likes")
    protected int totalLikes;
    @SerializedName("total_photos")
    protected int totalPhotos;
    @SerializedName("profile_image")
    protected ProfileImage profileImage = new ProfileImage();

    public User(){}

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }
}
