package com.android.apnaott;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.MyViewHolder> {

    private Context context;
    private List<DataModel> dataModel;

    public SliderAdapter(Context context, List<DataModel> dataModel) {
        this.context = context;
        this.dataModel = dataModel;
    }

    public void renewItems(List<DataModel> dataModels) {
        this.dataModel = dataModels;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.dataModel.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item_container, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel currentItem = dataModel.get(position);
        holder.trailer_title.setText(currentItem.getTitle());

        // Load image using Glide
        Glide.with(context).load(currentItem.getTurl()).into(holder.slider_image);

    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView slider_image;
        TextView trailer_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            slider_image = itemView.findViewById(R.id.img_thumbnail);
            trailer_title = itemView.findViewById(R.id.trailer_title);
        }
    }
}