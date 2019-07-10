package com.example.artgram.services;

import com.example.artgram.models.Collection;
import com.example.artgram.models.RecentPhotos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("photos")
    Call<List<RecentPhotos>> getPhotos();

    @GET("collections/featured")
    Call<List<Collection>> getCollections();

    @GET("collections/{id}")
    Call<Collection> getInformationOfCollection(@Path("id") int id);

    @GET("collections/{id}/photos")
    Call<List<RecentPhotos>> getPhotosOfCollection(@Path("id") int id);

    @GET("search/photos?")
    Call<List<RecentPhotos>> getSearchedPhotos(@Query("query") String query);
}
