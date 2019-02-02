package com.example.surakum2.moviefinder.model;

import android.widget.LinearLayout;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    private static MovieList mInstance = null;

    public static void setInstance() {
        if (mInstance == null) {
            mInstance = new MovieList();
        }
    }

    public static MovieList getInstance(){
        return mInstance;
    }

    private MovieList(){

    }

    @SerializedName("results")
    private List<MovieListModel> movieListModel;

    private MovieList movieList;

    public MovieList getMovieList() {
        return movieList;
    }

    public void setMovieList(MovieList movieList) {
        this.movieList = movieList;
    }

    public void setMovieListModel(List<MovieListModel> movieListModel) {
        this.movieListModel = movieListModel;
    }

    public List<MovieListModel> getMovieListModel() {
        return movieListModel;
    }
}
