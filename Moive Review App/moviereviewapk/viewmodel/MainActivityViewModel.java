package com.example.moviereviewapk.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviereviewapk.model.Movie;
import com.example.moviereviewapk.model.Movie_Repository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private Movie_Repository movieRepository;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new Movie_Repository(application);
    }

    //livedata
    public LiveData<List<Movie>> getAllMovies(){
        return movieRepository.getMutableLiveData();
    }

}

//  just creating instance of repository to get data & passing it to activity

