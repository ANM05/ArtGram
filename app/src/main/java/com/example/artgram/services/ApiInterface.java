package com.example.artgram.services;

import com.example.artgram.models.Collection;
import com.example.artgram.models.RecentPhotos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("photos")
    Call<List<RecentPhotos>> getPhotos();

    @GET("collections/featured")
    Call<List<Collection>> getCollections();

}
