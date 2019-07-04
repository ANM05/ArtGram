package com.example.artgram.services;

import android.util.Log;


import com.example.artgram.models.RecentPhotos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashService {

    private static Retrofit retrofit=null;
    private static Gson gson=new GsonBuilder().create();

    private static HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder okhttpBuilder=new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request=chain.request().newBuilder()
                            .addHeader("Authorization", "client_id"+Constants.UNSPLASH_KEY)
                            .build();

                    return chain.proceed(request);
                }
            });

    private static OkHttpClient okHttpClient = okhttpBuilder.build();

    public static <T> T createService(Class<T> serviceClass){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit.create(serviceClass);
    }
//    public static void getRecentPhotos(Callback callback){
//        OkHttpClient client=new OkHttpClient.Builder().build();
//
//        HttpUrl.Builder urlBuilder=HttpUrl.parse(Constants.BASE_URL).newBuilder();
//        urlBuilder.addQueryParameter("query", Constants.QUERY);
//        urlBuilder.addQueryParameter("client_id", Constants.UNSPLASH_KEY);
//
//        String url=urlBuilder.build().toString();
//
//        System.out.println(url);
//
//        Request request=new Request.Builder().url(url).build();
//        Call call=client.newCall(request);
//        call.enqueue(callback);
//    }
//
//    public ArrayList<RecentPhotos> processResults(Response response){
//        ArrayList<RecentPhotos> recentPhotos=new ArrayList<RecentPhotos>();
//        try{
//            String jsonData=response.body().string();
//            JSONObject unsplashJson=new JSONObject(jsonData);
//            JSONArray photosJsonArray =unsplashJson.getJSONArray("results");
//            if(response.isSuccessful()){
//                for(int index=0; index<photosJsonArray.length(); index++){
//                    JSONObject photoJson=photosJsonArray.getJSONObject(index);
//                    String id=photoJson.getString("id");
//                    String createdAt=photoJson.getString("created_at");
//                    String updatedAt=photoJson.getString("updated_at");
//                    String color=photoJson.getString("color");
//                    String description=photoJson.optString("description", "Description not available");
//                    String imageUrl=photoJson.getJSONObject("urls").getString("regular");
//                    String downloadUrl=photoJson.getJSONObject("links").getString("download");
//                    String userName=photoJson.getJSONObject("user").getString("username");
//                    String name=photoJson.getJSONObject("user").getString("name");
//                    String portfolioUrl=photoJson.getJSONObject("user").optString("portfolio_url", "Portfolio not available");
//                    String bio=photoJson.getJSONObject("user").optString("bio", "Bio not available");
//                    String location=photoJson.getJSONObject("user").optString("location", "Location not available");
//                    String profileImgUrl=photoJson.getJSONObject("user").getJSONObject("profile_image").getString("medium");
//                    Double totalLikes=photoJson.getJSONObject("user").getDouble("total_likes");
//                    Double totalPhotos=photoJson.getJSONObject("user").getDouble("total_photos");
//
//                    RecentPhotos photo=new RecentPhotos();
//                    recentPhotos.add(photo);
//                }
//            }
//
//
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        catch (JSONException e){
//            e.printStackTrace();
//        }
//    return recentPhotos;
//    }
}
