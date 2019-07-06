package com.example.artgram.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.artgram.R;
import com.example.artgram.adapters.CollectionsAdapter;
import com.example.artgram.models.Collection;
import com.example.artgram.services.ApiInterface;
import com.example.artgram.services.UnsplashService;
import com.example.artgram.utils.Functions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionsFragment extends Fragment {

    private final String TAG = CollectionsFragment.class.getSimpleName();

    @BindView(R.id.collections_gridView)
    GridView gridView;
    @BindView(R.id.collections_progressBar)
    ProgressBar collectionsProgessBar;

    private Unbinder unbinder;

    List<Collection> collectionList=new ArrayList<Collection>();
    private CollectionsAdapter adapter;


    public CollectionsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collections, container, false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new CollectionsAdapter(getActivity(), collectionList);
        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Collection collection = collections.get(i);
//                Log.d(TAG, collection.getId() + "");
//                Bundle bundle = new Bundle();
//                bundle.putInt("collectionId", collection.getId());
//                CollectionFragment collectionFragment = new CollectionFragment();
//                collectionFragment.setArguments(bundle);
//                Functions.changeMainFragment(getActivity(), collectionFragment);
//            }
//        });

        getCollections();
        showProgressBar(true);
        return view;
    }

    private void getCollections(){
        ApiInterface apiInterface = UnsplashService.createService(ApiInterface.class);
        Call<List<Collection>> call = apiInterface.getCollections();
        call.enqueue(new Callback<List<Collection>>() {
            @Override
            public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
                if(response.isSuccessful()){
                    for(Collection collection: response.body()){
                        collectionList.add(collection);
                    }
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, "size " + collectionList.size());
                }else{
                    Log.d(TAG, "fail " + response.message());
                }
                showProgressBar(false);
            }

            @Override
            public void onFailure(Call<List<Collection>> call, Throwable t) {
                Log.d(TAG, t.getMessage());
                showProgressBar(false);
            }
        });
    }

    private void showProgressBar(boolean isShow){
        if(isShow){
            collectionsProgessBar.setVisibility(View.VISIBLE);
            gridView.setVisibility(View.GONE);
        }else{
            collectionsProgessBar.setVisibility(View.GONE);
            gridView.setVisibility(View.VISIBLE);
        }

    }

    @OnItemClick(R.id.collections_gridView)
    public void onItemClick(int position){
        Collection collection = collectionList.get(position);
        Log.d(TAG, collection.getId() + "");
        Bundle bundle = new Bundle();
        bundle.putInt("collectionId", collection.getId());
        CollectionViewFragment collectionViewFragment = new CollectionViewFragment();
        collectionViewFragment.setArguments(bundle);
        Functions.changeMainFragmentWithBack(getActivity(), collectionViewFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
