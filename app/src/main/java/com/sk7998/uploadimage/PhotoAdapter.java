// PhotoAdapter.java
package com.sk7998.uploadimage;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private List<Bitmap> photoBitmaps;

    public PhotoAdapter() {
        this.photoBitmaps = new ArrayList<>();
    }

    public void addPhoto(Bitmap bitmap) {
        photoBitmaps.add(bitmap);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Bitmap bitmap = photoBitmaps.get(position);
        Glide.with(holder.itemView.getContext())
                .load(bitmap)
                .fitCenter()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photoBitmaps.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}