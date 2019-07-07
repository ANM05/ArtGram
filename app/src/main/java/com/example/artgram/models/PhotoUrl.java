package com.example.artgram.models;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class PhotoUrl {
    @SerializedName("raw")
    protected String raw;
    @SerializedName("full")
    protected String full;
    @SerializedName("regular")
    protected String regular;

    public PhotoUrl(){}

    public String getRaw() {
        return raw;
    }

    public String getFull() {
        return full;
    }

    public String getRegular() {
        return regular;
    }
}
