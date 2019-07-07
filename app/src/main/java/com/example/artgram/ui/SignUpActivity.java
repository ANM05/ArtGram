package com.example.artgram.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.artgram.R;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.loading)
    AVLoadingIndicatorView loadingIndicatorView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        loadingIndicatorView.hide();
    }
}
