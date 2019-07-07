package com.example.artgram.adapters;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.artgram.R;
import com.example.artgram.models.Collection;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAdapter extends BaseAdapter {

    private List<Collection> collections;
    private Context context;

    public CollectionsAdapter(Context context, List<Collection> collections){
        this.collections = collections;
        this.context = context;
    }

    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int i) {
        return collections.get(i);
    }

    @Override
    public long getItemId(int i) {
        return collections.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.holder_collections, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        ButterKnife.bind(this, view);
        Collection collection = (Collection) collections.get(i);

        if(collection.getTitle() != null){
            Log.d("Title", collection.getTitle());
            holder.title.setText(collection.getTitle());

        }
        holder.totalPhotos.setText(String.valueOf(collection.getTotalPhotos()) + " photos");
        Picasso.get().load(collection.getCoverPhoto().getUrl().getRegular()).into(holder.collectionPhoto);
        return view;

    }

    static class ViewHolder{
        @BindView(R.id.collection_photo)
        ImageView collectionPhoto;
        @BindView(R.id.collection_title) TextView title;
        @BindView(R.id.collection_total_photos) TextView totalPhotos;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
