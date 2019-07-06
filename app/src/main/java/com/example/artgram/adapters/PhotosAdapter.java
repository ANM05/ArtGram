package com.example.artgram.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.artgram.R;
import com.example.artgram.models.RecentPhotos;
import com.example.artgram.ui.DetailsActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private Context mContext;
    private List<RecentPhotos> mPhotos=new ArrayList<>();

    public PhotosAdapter(Context context, List<RecentPhotos> photos){
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

    public class PhotosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgMain) ImageView photo;

        Context mContext;

        public PhotosViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext=itemView.getContext();

            itemView.setOnClickListener(this);
        }

        public void bindRecentPhotos(RecentPhotos recentPhotos){
            Picasso.get().load(recentPhotos.getUrl().getRegular()).into(photo);

        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("photos", Parcels.wrap(mPhotos));
            mContext.startActivity(intent);
        }
    }

}
