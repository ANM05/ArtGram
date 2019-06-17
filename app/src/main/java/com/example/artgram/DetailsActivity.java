package com.example.artgram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView fullscreenImgView=(ImageView)findViewById(R.id.imgFullScreen);

        Intent intent=new Intent();
        fullscreenImgView.setImageResource(intent.getIntExtra("images", 0));

    }
}
