package com.example.artgram.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.artgram.R;
import com.example.artgram.models.RecentPhotos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.PhotosViewHolder>{

    private Context context;
    private List<RecentPhotos> photosSearched=new ArrayList<>();

    public SearchAdapter(Context context, List<RecentPhotos> photosSearched) {
        this.context = context;
        this.photosSearched = photosSearched;
    }

    @NonNull
    @Override
    public SearchAdapter.PhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_custom, parent, false);
        SearchAdapter.PhotosViewHolder viewHolder=new SearchAdapter.PhotosViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchAdapter.PhotosViewHolder holder, int position) {
        holder.bindRecentPhotos(photosSearched.get(position));
    }

    @Override
    public int getItemCount() {
        return photosSearched.size();
    }


    public class PhotosViewHolder extends RecyclerView.ViewHolder{
        Context context;

        @BindView(R.id.imgSearch)
        ImageView searchImg;
        @BindView(R.id.search_userName)
        TextView searchUsername;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context=itemView.getContext();
        }

        public void bindRecentPhotos(RecentPhotos recentPhotos){
            Picasso.get().load(recentPhotos.getUrl().getRegular()).into(searchImg);
            searchUsername.setText(photosSearched.get(0).getUser().getUsername());

        }
    }
}
