package com.example.moviereviewapk.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.moviereviewapk.R;
import com.example.moviereviewapk.adapters.MovieAdapter;
import com.example.moviereviewapk.databinding.ActivityMainBinding;
import com.example.moviereviewapk.databinding.ActivityMovieBinding;
import com.example.moviereviewapk.model.Movie;
import com.example.moviereviewapk.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Movie Apk");

        // initializing activityMainBinding & mainActivityViewModel
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        getPopularMovies();

        // initializing recycleView and setting onClickListener
        swipeRefreshLayout = activityMainBinding.swipeLayout;
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopularMovies();
            }
        });

    }

    // method is responsible to observe the change in database and update it in UI
    private void getPopularMovies() {
        mainActivityViewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> moviesFromLiveData) {
                movies = (ArrayList<Movie>) moviesFromLiveData;         // movie instance will be created & passed to adapter (if there is change in data)
                showOnRecyclerView();           // method is responsible to show fetched data into recycle view
            }
        });
    }


    // method is responsible to load data into recycleView via adapter
    private void showOnRecyclerView() {
        recyclerView = activityMainBinding.rvMovies;

        movieAdapter = new MovieAdapter(this, movies);
        // adjusting grid layout based on orientation of device Portrait / Landscape
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();
    }
}