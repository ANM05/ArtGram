//package com.example.artgram.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Parcel;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.example.artgram.R;
//import com.example.artgram.adapters.PhotosPagerAdapter;
//import com.example.artgram.models.RecentPhotos;
//
//import org.parceler.Parcels;
//
//import java.util.ArrayList;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class DetailsActivity extends AppCompatActivity {
//    @BindView(R.id.viewPager)
//    ViewPager mViewPager;
//    private PhotosPagerAdapter pagerAdapter;
//    ArrayList<RecentPhotos> mRecentPhotos=new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_details);
//        ButterKnife.bind(this);
//
//        mRecentPhotos= Parcels.unwrap(getIntent().getParcelableExtra("photos"));
//        int startingPosition = getIntent().getIntExtra("position", 0);
//
//        pagerAdapter=new PhotosPagerAdapter(getSupportFragmentManager(), mRecentPhotos);
//        mViewPager.setAdapter(pagerAdapter);
//        mViewPager.setCurrentItem(startingPosition);
//    }
//}
