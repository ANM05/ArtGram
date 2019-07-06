package com.example.artgram.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.artgram.R;

public class Functions {

    public static void changeMainFragmentWithBack(FragmentActivity fragmentActivity, Fragment fragment){
        fragmentActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
