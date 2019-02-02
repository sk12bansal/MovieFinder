package com.example.surakum2.moviefinder.rest;

import com.example.surakum2.moviefinder.BuildConfig;
import com.example.surakum2.moviefinder.model.MovieList;


import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieDbEndPoints {

    String apiKey = BuildConfig.APIKEY;

    @GET("3/movie/upcoming" + apiKey)
    Call<MovieList> getUpComingMovieList();


    @GET("3/movie/now_playing" + apiKey)
    Call<MovieList> getNowPlayingMovieList();


    @GET("3/movie/popular" + apiKey)
    Call<MovieList> getPopularMovieList();

    @GET("3/movie/top_rated" + apiKey)
    Call<MovieList> getTopRatedMovieList();
}
