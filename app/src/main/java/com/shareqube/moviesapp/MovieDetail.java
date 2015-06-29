package com.shareqube.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MovieDetail extends AppCompatActivity {

    static String Transision_name = "MOVIE DETAIL";
    String LOG_TAG = MovieDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (savedInstanceState == null) {

            Bundle bundle = new Bundle();
            bundle.putParcelable("movie", getIntent().getData());
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_fragment, movieDetailFragment)
                    .commit();
        }





    }


}
