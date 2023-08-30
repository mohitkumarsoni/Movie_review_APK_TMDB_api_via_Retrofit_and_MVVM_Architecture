package com.example.moviereviewapk.service;

import com.example.moviereviewapk.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Movie_Data_Service {

    //BASE URL
    //https://api.themoviedb.org/3/

    //entering end point url
    // movie/popular?api_key=0cc8e6c87c38347e437f4a4d62aede82
    //wherever you see '?' you need to pass Query & Query will be passes in "Response/model class" i.e <Result> here
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String api_key );

}
