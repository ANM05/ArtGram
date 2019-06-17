package com.example.artgram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mDescription;
    private int[] mImages;

    MainAdapter(Context context, String[] description, int[] images){
        super(context, R.layout.main_custom, R.id.img_text, description);
        this.mContext=context;
        this.mDescription=description;
        this.mImages=images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View main_custom = layoutInflater.inflate(R.layout.main_custom, parent, false);

        ImageView imageView = main_custom.findViewById(R.id.img_main);
        TextView desc = main_custom.findViewById(R.id.img_text);

        imageView.setImageResource(mImages[position]);
        desc.setText(mDescription[position]);

        return main_custom;
    }
}
