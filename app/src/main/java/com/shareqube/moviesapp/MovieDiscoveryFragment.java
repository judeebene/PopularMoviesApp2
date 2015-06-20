package com.shareqube.moviesapp;

/**
 * Created by Jude Ben on 6/8/2015.
 */


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.shareqube.moviesapp.adapter.MoviesAdapter;
import com.shareqube.moviesapp.sync.MovieSyncAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MovieDiscoveryFragment extends Fragment {

    String LOG_TAG = MovieDiscoveryFragment.class.getSimpleName();

    List<Movie> result  =  MovieSyncAdapter.getMovieList();

    Movie movie;


    MoviesAdapter mMoviesAdapter;


    public MovieDiscoveryFragment() {

    }

    public interface CallerBack {

        public void onMovieSelected(Movie m);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    // saving the data into Parcelable object
    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (movie != null) {
            outState.putParcelable("mMovie", movie);
        }
        super.onSaveInstanceState(outState);
    }

    // restoring parcelable data on device change
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            movie = savedInstanceState.getParcelable("mMovie");
        }
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_discovery_screen, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case R.id.action_settings:

                Intent settingIntent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(settingIntent);
                break;

            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    public void updateMovies() {


        MovieSyncAdapter.syncImmediately(getActivity());


    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mMoviesAdapter = new MoviesAdapter(getActivity(), R.layout.fragment_discovery_screen, result);

        GridView moviesPosterGrid = (GridView) getActivity().findViewById(R.id.movie_gridview);

        moviesPosterGrid.setAdapter(mMoviesAdapter);
        View rootView = inflater.inflate(R.layout.fragment_discovery_screen, container, false);


        return rootView;
    }





}

