package com.example.artgram.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.artgram.fragments.PhotoDetailFragment;
import com.example.artgram.models.RecentPhotos;

import java.util.ArrayList;

public class PhotosPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<RecentPhotos> mRecentPhotos;

    public PhotosPagerAdapter(FragmentManager fm, ArrayList<RecentPhotos> recentPhotos) {
        super(fm);
        mRecentPhotos = recentPhotos;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoDetailFragment.newInstance(mRecentPhotos.get(position));
    }

    @Override
    public int getCount() {
        return mRecentPhotos.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRecentPhotos.get(position).getUser().getUsername();
    }
}
