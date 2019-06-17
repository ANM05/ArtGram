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

        Intent callIntent=getIntent();
        if(callIntent !=null){
            Uri imageUri=callIntent.getData();
            if(imageUri !=null && fullscreenImgView !=null){
                Glide.with(this).load(imageUri).into(fullscreenImgView);
            }
        }
    }
}
