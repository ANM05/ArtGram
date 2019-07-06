package com.example.artgram.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.artgram.R;
import com.example.artgram.models.RecentPhotos;
import com.example.artgram.utils.GlideApp;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoDetailFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.descriptionImageView)
    ImageView mphotoImageView;
    @BindView(R.id.photoDescription)
    TextView mPhotoDescription;

    private RecentPhotos mRecentPhotos;
    private Context context;

    public PhotoDetailFragment() {
        // Required empty public constructor
    }

    public static PhotoDetailFragment newInstance(RecentPhotos recentPhotos){
        PhotoDetailFragment detailFragment=new PhotoDetailFragment();
        Bundle args=new Bundle();
        args.putParcelable("recentPhotos", Parcels.wrap(recentPhotos));
        detailFragment.setArguments(args);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecentPhotos = Parcels.unwrap(getArguments().getParcelable("recentPhotos"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        ButterKnife.bind(this, view);
        GlideApp
                .with(context)
                .load(mRecentPhotos.getUrl().getRegular())
                .into(mphotoImageView);
        mPhotoDescription.setText(mRecentPhotos.getDescription());

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
