package com.shareqube.popularmoviesapp;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class DiscoveryScreen extends AppCompatActivity implements MovieDiscoveryFragment.CallerBack {
    Boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discovery_screen);

        PreferenceManager.setDefaultValues(this, R.xml.movies_setting, false);
  /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_discovery_fragment, new MovieDiscoveryFragment())
                    .commit();
        }
        */
        if (findViewById(R.id.movie_detail_fragment) != null) {

            mTwoPane = true;
            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.movie_discovery_fragment, new MovieDiscoveryFragment())
                        .commit();

            } else {
                mTwoPane = false;

            }

            Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
            toolbar.setNavigationIcon(R.mipmap.ic_launcher);
            toolbar.setTitle(R.string.app_name);
            setSupportActionBar(toolbar);




        }


    }

    @Override
    public void onMovieSelected(int position) {
        
    }
}