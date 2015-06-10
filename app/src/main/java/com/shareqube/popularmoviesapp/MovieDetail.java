package com.shareqube.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class MovieDetail extends AppCompatActivity {

    String LOG_TAG = MovieDetail.class.getSimpleName() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true) ;
            actionBar.show();
        }
        Log.e(LOG_TAG , "ActionBar in Detail " + actionBar);




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        String LOG_TAG = PlaceholderFragment.class.getSimpleName() ;

        ViewHolder holder ;

        public PlaceholderFragment() {
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


                Log.e(LOG_TAG ," Detail Images" + movieDetails.getmMovieposter());
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
            TextView  movieTitle ;
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
}
