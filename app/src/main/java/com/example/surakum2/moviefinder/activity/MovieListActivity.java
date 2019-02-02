package com.example.surakum2.moviefinder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.surakum2.moviefinder.R;
import com.example.surakum2.moviefinder.adapter.movieListAdapter;
import com.example.surakum2.moviefinder.model.MovieList;
import com.example.surakum2.moviefinder.model.MovieListModel;
import com.example.surakum2.moviefinder.rest.APIClient;
import com.example.surakum2.moviefinder.rest.MovieDbEndPoints;
import com.example.surakum2.moviefinder.utility.ClientUiCommon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    List<MovieListModel> myUpComingDataSource = new ArrayList<>();
    List<MovieListModel> myNowPlayingDataSource = new ArrayList<>();
    List<MovieListModel> myPopularDataSource = new ArrayList<>();
    List<MovieListModel> myTopratedDataSource = new ArrayList<>();
    private List<MainSecionType> mainSecionList;

    public enum MainSecionType {
        UPCOMING,NOW_PLAYING,POPULAR,TOP_RATED
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);

        mainSecionList = ClientUiCommon.getDefaultMainSecionList();
        for (int i = 0; i < mainSecionList.size(); i++) {
            MainSecionType mainSecionType = mainSecionList.get(i);
            switch (mainSecionType) {
                case UPCOMING:
                    recyclerView = findViewById(R.id.upcoming_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myUpComingDataSource,R.layout.movie_list_item,getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                    loadUpComingMovieList();
                    break;
                case NOW_PLAYING:
                    recyclerView = findViewById(R.id.nowPlaying_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myNowPlayingDataSource,R.layout.movie_list_item,getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                    loadNowPlayingMovieList();
                    break;
                case POPULAR:
                    recyclerView = findViewById(R.id.popular_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myPopularDataSource,R.layout.movie_list_item,getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                    loadPopularMovieList();
                    break;
                case TOP_RATED:
                    recyclerView = findViewById(R.id.toprated_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myTopratedDataSource,R.layout.movie_list_item,getApplicationContext());
                    recyclerView.setAdapter(myAdapter);
                    loadTopRatedMovieList();
                    break;


            }
        }

    }

    public void loadUpComingMovieList(){
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getUpComingMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response!=null){

                    MovieList movieList = response.body();
                    myUpComingDataSource.clear();
                    for (int i = 0; i < movieList.getMovieListModel().size(); i++) {
                        myUpComingDataSource.add(movieList.getMovieListModel().get(i));
                    }

                    myAdapter.notifyDataSetChanged();
                }
                Log.e("UpComingMovieList", response.toString());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Log error here since request failed
                Log.e("UpComingMovieList", t.toString());
            }
        });

    }


    public void loadNowPlayingMovieList(){
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getNowPlayingMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response!=null){

                    MovieList movieList = response.body();
                    myNowPlayingDataSource.clear();
                    for (int i = 0; i < movieList.getMovieListModel().size(); i++) {
                        myNowPlayingDataSource.add(movieList.getMovieListModel().get(i));
                    }

                    myAdapter.notifyDataSetChanged();
                }
                Log.e("NowPlayingMovieList", response.toString());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Log error here since request failed
                Log.e("NowPlayingMovieList", t.toString());
            }
        });

    }

    public void loadPopularMovieList(){
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getPopularMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response!=null){

                    MovieList movieList = response.body();
                    myPopularDataSource.clear();
                    for (int i = 0; i < movieList.getMovieListModel().size(); i++) {
                        myPopularDataSource.add(movieList.getMovieListModel().get(i));
                    }

                    myAdapter.notifyDataSetChanged();
                }
                Log.e("PopularMovieList", response.toString());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Log error here since request failed
                Log.e("PopularMovieList", t.toString());
            }
        });

    }


    public void loadTopRatedMovieList(){
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getTopRatedMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if(response!=null){

                    MovieList movieList = response.body();
                    myTopratedDataSource.clear();
                    for (int i = 0; i < movieList.getMovieListModel().size(); i++) {
                        myTopratedDataSource.add(movieList.getMovieListModel().get(i));
                    }

                    myAdapter.notifyDataSetChanged();
                }
                Log.e("TopRatedMovieList", response.toString());
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                // Log error here since request failed
                Log.e("TopRatedMovieList", t.toString());
            }
        });

    }
}
