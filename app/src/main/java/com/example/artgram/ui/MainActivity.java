package com.example.artgram.ui;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgram.R;

import com.example.artgram.services.UnsplashService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG= MainActivity.class.getSimpleName();
    @BindView(R.id.mainRecycler)
    RecyclerView mRecycler;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

//    public ArrayList<RecentPhotos> mPhotos=new ArrayList<>();
//    private PhotosAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getPhotos();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void getPhotos(){
        final UnsplashService flickrService=new UnsplashService();

        flickrService.getRecentPhotos(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call,  Response response) throws IOException {
//                mPhotos=flickrService.processResults(response);
//
//                MainActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mAdapter = new PhotosAdapter(getApplicationContext(), mPhotos);
//                        mRecycler.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new GridLayoutManager(getApplicationContext(),2);
//                        mRecycler.setLayoutManager(layoutManager);
//                        mRecycler.setHasFixedSize(true);
//
//                    }
//                });
            }
        });
    }
}

