package com.shareqube.moviesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

/**
 * Created by Jude Ben on 6/18/2015.
 */
public class Utility {

    public static String getPreferedMovieSorted(Context context) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String sort_order = preferences.getString(context.getString(R.string.movie_sort_key),
                context.getString(R.string.movie_sort_order_default_value));


        return sort_order;

    }


    public static String getMoviePosterAbsolutePath(String relative_path) {

        final String BASE_MOVIE_POSTER_URL = "http://image.tmdb.org/t/p/";
        String[] IMAGE_SIZES = {"w185", "w92", "w154", "w342", "w500", "w780"};


        return BASE_MOVIE_POSTER_URL + IMAGE_SIZES[0] + relative_path;
    }

    public static Boolean isNetworkAvailable(Context c) {

        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }
}
