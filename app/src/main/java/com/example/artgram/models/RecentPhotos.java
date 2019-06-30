package com.example.artgram.models;

import org.parceler.Parcel;

@Parcel

public class RecentPhotos {

    private String mId;
    private String mOwnerId;
    private String mSecret;
    private String mServer;
    private int farm;
    private String mTitle;
    private int mPublic;
    private int mFriend;
    private int mFamily;
    private String mImageUrl;
    private int height;
    private int width;

    public RecentPhotos(){}

    public RecentPhotos(String mId, String mOwnerId, String mSecret, String mServer, int farm, String mTitle, int mPublic, int mFriend, int mFamily, String mImageUrl, int height, int width) {
        this.mId = mId;
        this.mOwnerId = mOwnerId;
        this.mSecret = mSecret;
        this.mServer = mServer;
        this.farm = farm;
        this.mTitle = mTitle;
        this.mPublic = mPublic;
        this.mFriend = mFriend;
        this.mFamily = mFamily;
        this.mImageUrl = mImageUrl;
        this.height = height;
        this.width = width;
    }

    public String getmId() {
        return mId;
    }

    public String getmOwnerId() {
        return mOwnerId;
    }

    public String getmSecret() {
        return mSecret;
    }

    public String getmServer() {
        return mServer;
    }

    public int getFarm() {
        return farm;
    }

    public String getmTitle() {
        return mTitle;
    }

    public int getmPublic() {
        return mPublic;
    }

    public int getmFriend() {
        return mFriend;
    }

    public int getmFamily() {
        return mFamily;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
