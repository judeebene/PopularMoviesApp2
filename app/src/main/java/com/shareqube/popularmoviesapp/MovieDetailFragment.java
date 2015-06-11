package com.shareqube.popularmoviesapp;

/**
 * Created by Jude Ben on 6/11/2015.
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public  class MovieDetailFragment extends Fragment {
    String LOG_TAG = MovieDetailFragment.class.getSimpleName() ;

    ViewHolder holder ;

    public MovieDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_setting , menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId() ;

        switch (id){
            case R.id.action_settings :
                Intent settingInent  = new Intent(getActivity() ,SettingsActivity.class) ;
                startActivity(settingInent);
                break;

            default:
                return false ;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        Intent intent = getActivity().getIntent() ;
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        holder = new ViewHolder(rootView) ;
        rootView.setTag(holder);
        if(intent != null & intent.hasExtra("movie")){
            Movie movieDetails =  intent.getParcelableExtra("movie");


            Log.e(LOG_TAG, " Detail Images" + movieDetails.getmMovieposter());
            Glide.with(this).load(movieDetails.getmMovieposter()).error(R.drawable.installerposter)
                    .override(400,400)
                    .into(holder.detailPoster);
            holder.movieOverview.setText(movieDetails.getmMovieOverview());
            holder.movieRelease.setText(movieDetails.getmMovieReleaseDate());
            holder.movieTitle.setText(movieDetails.getmMovietitle());

            Float rating = Float.parseFloat(movieDetails.getmMovieRating());
            holder.movieRating.setRating(rating);

            holder.movieRatingValue.setText(movieDetails.getmMovieRating());




        }


        return rootView;
    }

    static class ViewHolder{
        ImageView detailPoster ;
        TextView movieTitle ;
        TextView  movieRelease ;
        TextView movieOverview ;
        RatingBar movieRating ;
        TextView  movieRatingValue ;

        public ViewHolder(View v){
            detailPoster = (ImageView) v.findViewById(R.id.detailPoster_view);
            movieTitle = (TextView) v.findViewById(R.id.movie_title_view) ;
            movieRelease = (TextView)v.findViewById(R.id.movie_release_view) ;
            movieOverview = (TextView) v.findViewById(R.id.movie_overview_view);
            movieRating = ( RatingBar) v.findViewById(R.id.movieRatingBar) ;
            movieRatingValue = (TextView) v.findViewById(R.id.movieRatingValue);
        }

    }
}