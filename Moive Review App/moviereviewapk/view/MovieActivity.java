package com.example.moviereviewapk.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.moviereviewapk.R;
import com.example.moviereviewapk.databinding.ActivityMovieBinding;
import com.example.moviereviewapk.model.Movie;

public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    private ActivityMovieBinding activityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        activityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Intent intent = getIntent();
        if (intent.hasExtra("movie")){
            movie = getIntent().getParcelableExtra("movie");
            activityMovieBinding.setMovie(movie);                       //setting movie instance in binding object, values will automatically be updated in UI
            getSupportActionBar().setTitle(movie.getTitle());
        }
    }
}

// selected movie details will be displayed in this activity
// kindly go through XML file to get quick idea about activity
