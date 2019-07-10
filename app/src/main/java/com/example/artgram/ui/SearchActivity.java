package com.example.artgram.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.artgram.R;
import com.example.artgram.adapters.SearchAdapter;
import com.example.artgram.models.RecentPhotos;
import com.example.artgram.services.ApiInterface;
import com.example.artgram.services.Constants;
import com.example.artgram.services.UnsplashService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mQuery;
    private List<RecentPhotos> mPhotos = new ArrayList<>();
    private SearchAdapter mAdapter;

    @BindView(R.id.searchView_recycler)
    RecyclerView mRecycler;
    @BindView(R.id.search_progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        showProgressBar(true);
        getSearchedPhotos(mQuery);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mQuery = mSharedPreferences.getString(Constants.QUERY, null);

        if (mQuery != null) {
            getSearchedPhotos(mQuery);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getSearchedPhotos(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void getSearchedPhotos(String query) {
        ApiInterface apiInterface = UnsplashService.createService(ApiInterface.class);
        Call<List<RecentPhotos>> call = apiInterface.getSearchedPhotos(query);

        call.enqueue(new Callback<List<RecentPhotos>>() {
            @Override
            public void onFailure(Call<List<RecentPhotos>> call, Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onResponse(Call<List<RecentPhotos>> call, Response<List<RecentPhotos>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Loading successfully, size: " + response.body().size());
                    for (RecentPhotos photo : response.body()) {
                        mPhotos.add(photo);
                        Log.d(TAG, photo.getUrl().getFull());
                    }
                    SearchActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new SearchAdapter(getApplicationContext(), mPhotos);
                            mRecycler.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager =
                                    new GridLayoutManager(getApplicationContext(), 2);
                            mRecycler.setLayoutManager(layoutManager);
                            mRecycler.setHasFixedSize(true);

                        }
                    });

                } else {
                    Log.d(TAG, "Fail" + response.message());
                }
                showProgressBar(false);
            }
        });
    }

    private void addToSharedPreferences(String query) {
        mEditor.putString(Constants.QUERY, query).apply();
    }

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
    }

}
