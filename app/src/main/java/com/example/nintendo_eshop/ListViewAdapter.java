package com.example.nintendo_eshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<GameItem> list;

    public ListViewAdapter(Context context, List<GameItem> list) {
        this.context = context;
        inflater= LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameItem game = list.get(position);

        holder.textTitle.setText(game.getTitle());
        holder.textCategories.setText(String.valueOf(game.getCategories()));
        Glide.with(context).load("http:" + game.getImage()).into(holder.image);
        //Picasso.get().load(game.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textTitle, textCategories;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.Title);
            textCategories = itemView.findViewById(R.id.Category);
            image = itemView.findViewById(R.id.poster);
        }
    }

}