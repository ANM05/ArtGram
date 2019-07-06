package com.example.artgram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgram.R;
import com.example.artgram.adapters.PhotosAdapter;
import com.example.artgram.models.Collection;
import com.example.artgram.models.RecentPhotos;
import com.example.artgram.services.ApiInterface;
import com.example.artgram.services.UnsplashService;
import com.example.artgram.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionViewFragment extends Fragment {

    private final String TAG = CollectionViewFragment.class.getSimpleName();
    @BindView(R.id.collections_recycleView) RecyclerView photosRecyclerView;
    @BindView(R.id.collections_view_progressbar)
    ProgressBar progressBar;
    @BindView(R.id.fragment_collection_user_avatar)
    CircleImageView userAvatar;
    @BindView(R.id.fragment_collection_username)
    TextView username;
    @BindView(R.id.fragment_collection_title)
    TextView title;
    @BindView(R.id.fragment_collection_description)
    TextView description;


    private Unbinder unbinder;

    private PhotosAdapter photosAdapter;
    private List<RecentPhotos> photos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collections_view, container, false);
        unbinder = ButterKnife.bind(this, view);
        // RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        photosRecyclerView.setLayoutManager(linearLayoutManager);
        photosAdapter = new PhotosAdapter(getActivity(), photos);
        photosRecyclerView.setAdapter(photosAdapter);
        Bundle bundle = getArguments();
        int collectionId = bundle.getInt("collectionId");

        showProgressBar(true);
        getInformationOfCollection(collectionId);
        getPhotosOfCollection(collectionId);
        return view;
    }

    private void getInformationOfCollection(final int collectionId){
        ApiInterface apiInterface = UnsplashService.createService(ApiInterface.class);
        Call<Collection> call = apiInterface.getInformationOfCollection(collectionId);
        call.enqueue(new Callback<Collection>() {
            @Override
            public void onResponse(Call<Collection> call, Response<Collection> response) {
                if(response.isSuccessful()){
                    Collection collection = response.body();
                    title.setText(collection.getTitle());
                    description.setText(collection.getDescription());
                    username.setText(collection.getUser().getUsername());
                    GlideApp
                            .with(getActivity())
                            .load(collection.getUser().getProfileImage().getSmall())
                            .into(userAvatar);

                }else{
                    Log.d(TAG, "Fail" + response.message());
                }
            }
            @Override
            public void onFailure(Call<Collection> call, Throwable t) {
                Log.d(TAG, "Fail: " + t.getMessage());
            }
        });
    }


    private void getPhotosOfCollection(int collectionId){
        ApiInterface apiInterface = UnsplashService.createService(ApiInterface.class);
        Call<List<RecentPhotos>> call = apiInterface.getPhotosOfCollection(collectionId);
        call.enqueue(new Callback<List<RecentPhotos>>() {
            @Override
            public void onResponse(Call<List<RecentPhotos>> call, Response<List<RecentPhotos>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "Loading successfully, size: " + response.body().size());
                    for(RecentPhotos photo: response.body()){
                        photos.add(photo);
                        Log.d(TAG, photo.getUrl().getFull());
                    }
                    photosAdapter.notifyDataSetChanged();

                }else{
                    Log.d(TAG, "Fail" + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<RecentPhotos>> call, Throwable t) {
                Log.d(TAG, "Fail: " + t.getMessage());
                showProgressBar(false);
            }
        });
    }
    private void showProgressBar(boolean isShow){
        if(isShow){
            progressBar.setVisibility(View.VISIBLE);
            photosRecyclerView.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            photosRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
