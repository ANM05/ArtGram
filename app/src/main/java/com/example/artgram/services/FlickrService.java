package com.example.artgram.services;

import com.example.artgram.models.RecentPhotos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FlickrService {

    public static void getRecentPhotos(Callback callback){
        OkHttpClient client=new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder=HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("api_key", Constants.FLICKR_KEY);
        urlBuilder.addQueryParameter("format", Constants.FORMART);
        String url=urlBuilder.build().toString();

        System.out.println(url);

        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<RecentPhotos> processResults(Response response){
        ArrayList<RecentPhotos> photos=new ArrayList<RecentPhotos>();
        try{
            String jsonData=response.body().toString();
            JSONObject flickrJson=new JSONObject(jsonData);
            JSONArray photosJSON = flickrJson.getJSONArray("photo");
            if(response.isSuccessful()){
                for(int index=0; index<photosJSON.length(); index++){
                    JSONObject photoJson=photosJSON.getJSONObject(index);
                    String title=photoJson.getString("title");
                    String imageId=photoJson.getString("id");
                    String ownerId=photoJson.getString("owner");

                    RecentPhotos photo=new RecentPhotos(title, imageId, ownerId);
                    photos.add(photo);
                }
            }
        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
        catch (JSONException e){
            e.printStackTrace();
        }
    return photos;
    }
}
