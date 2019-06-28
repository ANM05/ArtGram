package com.example.artgram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgram.R;
import com.example.artgram.models.RecentPhotos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {
    private Context mContext;
    private ArrayList<RecentPhotos> mPhotos=new ArrayList<>();

    public PhotosAdapter(Context context, ArrayList<RecentPhotos> photos){
        this.mContext=context;
        this.mPhotos=photos;
    }

    @Override
    public PhotosAdapter.PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_custom, parent, false);
        PhotosViewHolder viewHolder=new PhotosViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotosAdapter.PhotosViewHolder holder, int position) {
        holder.bindRecentPhotos(mPhotos.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageOne) ImageView photo;
        @BindView(R.id.photoTitle) TextView title;
        private Context mContext;

        public PhotosViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();
        }

        public void bindRecentPhotos(RecentPhotos recentPhotos){
            Picasso.get().load(recentPhotos.getmImageUrl()).into(photo);
            title.setText(recentPhotos.getmTitle());
        }
    }

}
