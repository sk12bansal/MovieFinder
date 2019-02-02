package com.example.surakum2.moviefinder.model;

import com.google.gson.annotations.SerializedName;

public class MovieListModel {

    @SerializedName("poster_path")
    private String movieImage;

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private String rating;

    @SerializedName("release_date")
    private String releaseDate;

    public MovieListModel(String movieImage, String title, String rating, String releaseDate) {
        this.movieImage = movieImage;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
