package com.example.artgram.services;

import android.util.Log;

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
        urlBuilder.addQueryParameter("nojsoncallback", Constants.CALLBACK);
        urlBuilder.addQueryParameter("text", Constants.TEXT);
        urlBuilder.addQueryParameter("extras", Constants.EXTRAS);
        String url=urlBuilder.build().toString();

        System.out.println(url);

        Request request=new Request.Builder().url(url).build();
        Call call=client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<RecentPhotos> processResults(Response response){
        ArrayList<RecentPhotos> recentPhotos=new ArrayList<RecentPhotos>();
        try{
            String jsonData=response.body().string();
            JSONObject flickrJson=new JSONObject(jsonData);
            JSONObject photosJSON = flickrJson.getJSONObject("photos");
            if(response.isSuccessful()){
                JSONArray photosJsonArray =photosJSON.getJSONArray("photo");
                for(int index=0; index<photosJsonArray.length(); index++){
                    JSONObject photoJson=photosJsonArray.getJSONObject(index);
                    String id=photoJson.getString("id");
                    String owner=photoJson.getString("owner");
                    String secret=photoJson.getString("secret");
                    String server=photoJson.getString("server");
                    int farm=photoJson.getInt("farm");
                    String title=photoJson.getString("title");
                    int isPublic=photoJson.getInt("ispublic");
                    int friend=photoJson.getInt("isfriend");
                    int family=photoJson.getInt("isfamily");
                    String imageUrl=photoJson.optString("url_n");
                    int height=photoJson.getInt("height_n");
                    int width=photoJson.getInt("width_n");

                    RecentPhotos photo=new RecentPhotos(id, owner, secret, server, farm, title, isPublic, friend, family, imageUrl, height, width);
                    recentPhotos.add(photo);
                }
            }


        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    return recentPhotos;
    }
}
