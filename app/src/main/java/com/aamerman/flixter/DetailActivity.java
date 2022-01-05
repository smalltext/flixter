package com.aamerman.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aamerman.flixter.databinding.ActivityDetailBinding;
import com.aamerman.flixter.models.Movie;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("View movie details");

        Movie movie = getIntent().getParcelableExtra(MainActivity.KEY_ITEM_MOVIE);

        binding.setMovie(movie);
        Glide.with(this)
                .load(movie.getPosterPath())
                .into(binding.ivPoster);
        Glide.with(this)
                .load(movie.getBackdropPath())
                .into(binding.ivBack);
    }
}