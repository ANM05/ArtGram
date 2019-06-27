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
    public Context mContext;
    private List<RecentPhotos> mPhotos=new ArrayList<>();

    public PhotosAdapter(Context context, ArrayList<RecentPhotos> photos){
        this.mContext=context;
        this.mPhotos=photos;
    }

    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_custom, parent, false);
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        RecentPhotos photo=mPhotos.get(position);
        Picasso.get().load(photo.getmImageUrl()).into(holder.photo);
        holder.title.setText(photo.getmTitle());

    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class PhotosViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageOne) ImageView photo;
        @BindView(R.id.photoTitle) TextView title;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
           mContext=itemView.getContext();
        }
    }

}
