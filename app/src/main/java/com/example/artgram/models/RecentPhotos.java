package com.example.artgram.models;

import org.parceler.Parcel;

@Parcel

public class RecentPhotos {

    private String id;
    private String createdAt;
    private String updatedAt;
    private String color;
    private String description;
    private String imageUrl;
    private String downloadUrl;
    private String userName;
    private String name;
    private String portfolioUrl;
    private String bio;
    private String location;
    private String profileImageUrl;
    private Double totalLikes;
    private Double totalPhotos;

    public RecentPhotos(){}

    public RecentPhotos(String id, String createdAt, String updatedAt, String color, String description, String imageUrl, String downloadUrl, String userName, String name, String portfolioUrl, String bio, String location, String profileImageUrl, Double totalLikes, Double totalPhotos) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.color = color;
        this.description = description;
        this.imageUrl = imageUrl;
        this.downloadUrl = downloadUrl;
        this.userName = userName;
        this.name = name;
        this.portfolioUrl = portfolioUrl;
        this.bio = bio;
        this.location = location;
        this.profileImageUrl = profileImageUrl;
        this.totalLikes = totalLikes;
        this.totalPhotos = totalPhotos;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public Double getTotalLikes() {
        return totalLikes;
    }

    public Double getTotalPhotos() {
        return totalPhotos;
    }
}
