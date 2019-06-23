package com.example.artgram.services;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class FlickrService {

    public static void getRecentPhotos(Callback callback){
        OkHttpClient client=new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder=HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("api_key", Constants.FLICKR_KEY);
        String url=urlBuilder.build().toString();

        System.out.println(url);

        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(callback);
    }
}
