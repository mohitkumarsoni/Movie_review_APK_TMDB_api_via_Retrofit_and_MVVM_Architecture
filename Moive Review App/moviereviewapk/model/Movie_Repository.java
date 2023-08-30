package com.example.moviereviewapk.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviereviewapk.R;
import com.example.moviereviewapk.service.Movie_Data_Service;
import com.example.moviereviewapk.service.Retrofit_Instance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie_Repository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;


    public Movie_Repository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveData(){

        // Movie_Data_Service is an Interface which deals with API via Retrofit
        Movie_Data_Service movieDataService = Retrofit_Instance.getService();
        Call<Result> call = movieDataService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result!=null && result.getResults()!=null){
                    movies = (ArrayList<Movie>) result.getResults();    // getResults() returns ArrayList<Movie>
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

}

// there are 2 model classes
// 1. Result which is parent model class
// 2. Movie which is nested/child inside Result class
