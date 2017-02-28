package com.example.gauravsingh.restdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gaurav Singh on 26-02-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PlaceViewHolder> {
    Context mContext;
    ArrayList<MovieDTO> mDataset    ;
    RecyclerViewAdapter(Context context, ArrayList<MovieDTO> arrayList){
        mContext = context;
        mDataset = arrayList;
    }

    @Override
    public RecyclerViewAdapter.PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_view, parent, false);
        PlaceViewHolder placeViewHolder = new PlaceViewHolder(view);
        return placeViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.PlaceViewHolder holder, int position) {
        MovieDTO movieDTO = mDataset.get(position);
        if(!TextUtils.isEmpty(movieDTO.getPosterUrl())) {
            String url = Utilty.BASE_URL + movieDTO.getPosterUrl();
            Picasso.with(mContext).load(url).into(holder.mImageView);
        }
        holder.mTextView.setText(movieDTO.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final TextView mTextView;
        PlaceViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.imageView);
            mTextView=(TextView)view.findViewById(R.id.textView);
        }
    }
}
