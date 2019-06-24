package com.example.artgram.models;

public class RecentPhotos {

    private String mTitle;
    private String mImageId;
    private String mOwnerId;

    public RecentPhotos(String title, String imageId, String ownerId){
        this.mTitle=title;
        this.mImageId=imageId;
        this.mOwnerId=ownerId;

    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageId() {
        return mImageId;
    }

    public String getOwnerId() {
        return mOwnerId;
    }
}
