package com.shareqube.moviesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MovieDetail extends AppCompatActivity {

    String LOG_TAG = MovieDetail.class.getSimpleName();

    static String Transision_name = "MOVIE DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_fragment, new MovieDetailFragment())
                    .commit();
        }

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(getString(R.string.app_name));


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true) ;
            actionBar.show();
        }

*/


    }


}
