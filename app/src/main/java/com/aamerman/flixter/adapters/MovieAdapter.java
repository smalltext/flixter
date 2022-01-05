package com.aamerman.flixter.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aamerman.flixter.R;
import com.aamerman.flixter.databinding.ItemMovieBinding;
import com.aamerman.flixter.models.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public interface OnClickListener {
        void onItemClicked(int position);
    }

    Context context;
    List<Movie> movies;
    OnClickListener clickListener;

    public MovieAdapter(Context context, List<Movie> movies, OnClickListener clickListener) {
        this.context = context;
        this.movies = movies;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(context), parent, false);
        //View moveView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemMovieBinding binding;

        public ViewHolder(@NonNull View itemView, ItemMovieBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void bind(Movie movie) {
            binding.setMovie(movie);

            String imageUrl;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                imageUrl = movie.getPosterPath();
            } else {
                imageUrl = movie.getBackdropPath();
            }

            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.image_placeholder)
                    .into(binding.ivImage);

            binding.ivImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
        }
    }
}
