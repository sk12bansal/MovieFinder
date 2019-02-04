package com.example.surakum2.moviefinder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    List<MovieListModel> myUpComingDataSource = new ArrayList<>();
    List<MovieListModel> myNowPlayingDataSource = new ArrayList<>();
    List<MovieListModel> myPopularDataSource = new ArrayList<>();
    List<MovieListModel> myTopratedDataSource = new ArrayList<>();

    public enum MainSecionType {
        UPCOMING, NOW_PLAYING, POPULAR, TOP_RATED
    }

    private List<MainSecionType> mainSecionList;


    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.movie_list, container, false);

        mainSecionList = ClientUiCommon.getDefaultMainSecionList();
        for (int i = 0; i < mainSecionList.size(); i++) {
            MainSecionType mainSecionType = mainSecionList.get(i);
            switch (mainSecionType) {
                case UPCOMING:
                    recyclerView = view.findViewById(R.id.upcoming_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myUpComingDataSource, R.layout.movie_list_item, getContext());
                    recyclerView.setAdapter(myAdapter);
                    loadUpComingMovieList();
                    break;
                case NOW_PLAYING:
                    recyclerView = view.findViewById(R.id.nowPlaying_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myNowPlayingDataSource, R.layout.movie_list_item, getContext());
                    recyclerView.setAdapter(myAdapter);
                    loadNowPlayingMovieList();
                    break;
                case POPULAR:
                    recyclerView = view.findViewById(R.id.popular_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myPopularDataSource, R.layout.movie_list_item, getContext());
                    recyclerView.setAdapter(myAdapter);
                    loadPopularMovieList();
                    break;
                case TOP_RATED:
                    recyclerView = view.findViewById(R.id.toprated_movies_recycler_view);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                    myAdapter = new movieListAdapter(myTopratedDataSource, R.layout.movie_list_item, getContext());
                    recyclerView.setAdapter(myAdapter);
                    loadTopRatedMovieList();
                    break;


            }
        }
        return view;
    }


    public void loadUpComingMovieList() {
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getUpComingMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response != null) {

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


    public void loadNowPlayingMovieList() {
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getNowPlayingMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response != null) {

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

    public void loadPopularMovieList() {
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getPopularMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response != null) {

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


    public void loadTopRatedMovieList() {
        MovieDbEndPoints apiService = APIClient.getClient().create(MovieDbEndPoints.class);
        Call<MovieList> call = apiService.getTopRatedMovieList();

        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response != null) {

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