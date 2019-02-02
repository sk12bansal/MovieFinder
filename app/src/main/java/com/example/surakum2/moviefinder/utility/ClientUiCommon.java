package com.example.surakum2.moviefinder.utility;

import com.example.surakum2.moviefinder.activity.MovieListActivity;

import java.util.ArrayList;
import java.util.List;

public class ClientUiCommon {


    private static ClientUiCommon mInstance = null;
    private static List<MovieListActivity.MainSecionType> mainSecionTypes = new ArrayList<>();

    public static ClientUiCommon getInstance(){
        return mInstance;
    }

    public static void setInstance(){
        if(mInstance==null){
            mInstance = new ClientUiCommon();
            setMainSecionTypes(mainSecionTypes);
        }
    }


    private ClientUiCommon(){
    }


    public static List<MovieListActivity.MainSecionType> getDefaultMainSecionList() {
        return mainSecionTypes;
    }

    public static void setMainSecionTypes(List<MovieListActivity.MainSecionType> mainSecionTypes) {
        mainSecionTypes.add(MovieListActivity.MainSecionType.UPCOMING);
        mainSecionTypes.add(MovieListActivity.MainSecionType.NOW_PLAYING);
        mainSecionTypes.add(MovieListActivity.MainSecionType.POPULAR);
        mainSecionTypes.add(MovieListActivity.MainSecionType.TOP_RATED);
    }
}
