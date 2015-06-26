package com.shareqube.moviesapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.shareqube.moviesapp.sync.MovieSyncAdapter;


public class DiscoveryScreen extends AppCompatActivity implements MovieDiscoveryFragment.CallerBack {
    String LOG_TAG = DiscoveryScreen.class.getSimpleName();
    Boolean mTwoPane = false;
    String MOVIE_DETAIL_FRAGMENT_TAG = "MOVIEFRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_screen);

        PreferenceManager.setDefaultValues(this, R.xml.movies_setting, false);

        if (findViewById(R.id.movie_detail_fragment) != null) {

            mTwoPane = true;
            Toast.makeText(this, " Two pane up is what" + mTwoPane, Toast.LENGTH_LONG).show();
            if (savedInstanceState == null) {

                MovieDetailFragment mdf = new MovieDetailFragment();

                /// In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments

                //  mdf.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_fragment, mdf, MOVIE_DETAIL_FRAGMENT_TAG)
                        .commit();


            } else {


                mTwoPane = false;
                Toast.makeText(this, " Two pane down is what" + mTwoPane, Toast.LENGTH_LONG).show();


            }


            Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);


        }

        MovieSyncAdapter.initializeSyncAdapter(this);


    }


    @Override
    public void onMovieSelected(Uri uri) {


        if (mTwoPane) {
            Bundle args = new Bundle();
            args.putParcelable("movie", uri);

            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(args);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_fragment, movieDetailFragment, MOVIE_DETAIL_FRAGMENT_TAG)
                    .commit();
        } else {
            Toast.makeText(this, " mTwo pane is fale here", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MovieDetail.class);
            intent.setData(uri);
            startActivity(intent);
        }

    }
}