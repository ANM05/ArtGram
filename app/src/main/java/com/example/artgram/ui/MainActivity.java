package com.example.artgram.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgram.R;

import com.example.artgram.adapters.PhotosAdapter;
import com.example.artgram.fragments.CollectionsFragment;
import com.example.artgram.models.RecentPhotos;
import com.example.artgram.services.ApiInterface;
import com.example.artgram.services.UnsplashService;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.mainRecycler)
    RecyclerView mRecycler;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.nav_view) NavigationView navigationView;

    private List<RecentPhotos> mPhotos = new ArrayList<>();
    private PhotosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        showProgressBar(true);
        getPhotos();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void getPhotos() {
        ApiInterface apiInterface = UnsplashService.createService(ApiInterface.class);
        Call<List<RecentPhotos>> call = apiInterface.getPhotos();

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
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAdapter = new PhotosAdapter(getApplicationContext(), mPhotos);
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

    private void showProgressBar(boolean isShow) {
        if (isShow) {
            progressBar.setVisibility(View.VISIBLE);
            mRecycler.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_collections:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CollectionsFragment()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

