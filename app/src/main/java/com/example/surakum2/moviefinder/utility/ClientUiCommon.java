package com.example.surakum2.moviefinder.utility;

import com.example.surakum2.moviefinder.fragment.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

public class ClientUiCommon {


    private static ClientUiCommon mInstance = null;
    private static List<MovieListFragment.MainSecionType> mainSecionTypes = new ArrayList<>();

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


    public static List<MovieListFragment.MainSecionType> getDefaultMainSecionList() {
        return mainSecionTypes;
    }

    public static void setMainSecionTypes(List<MovieListFragment.MainSecionType> mainSecionTypes) {
        mainSecionTypes.add(MovieListFragment.MainSecionType.UPCOMING);
        mainSecionTypes.add(MovieListFragment.MainSecionType.NOW_PLAYING);
        mainSecionTypes.add(MovieListFragment.MainSecionType.POPULAR);
        mainSecionTypes.add(MovieListFragment.MainSecionType.TOP_RATED);
    }
}
