package com.example.artgram.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class ProfileImage {
    @SerializedName("small")
    protected String small;
    @SerializedName("medium")
    protected String medium;
    @SerializedName("large")
    protected String large;

    public ProfileImage(){}

    public String getSmall() {
        return small;
    }

    public String getMedium() {
        return medium;
    }

    public String getLarge() {
        return large;
    }
}
