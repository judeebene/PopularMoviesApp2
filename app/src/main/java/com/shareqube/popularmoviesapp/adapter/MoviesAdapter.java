package com.shareqube.popularmoviesapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shareqube.popularmoviesapp.Movie;
import com.shareqube.popularmoviesapp.MovieDiscoveryFragment;
import com.shareqube.popularmoviesapp.R;

import java.util.List;

/**
 * Created by Jude Ben on 6/7/2015.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    String LOG_TAG = MoviesAdapter.class.getSimpleName() ;

    static Context mContext;
    List<Movie> movie_poster;
    List<String> path ;


    int resouse_id ;


    MovieDiscoveryFragment movieDiscoveryFragment;


    // ignoring   defaut text view id with super
    public MoviesAdapter(Context context, int resId , List<Movie> movies) {
        super(context ,resId ,movies);

        resouse_id = resId ;
        movie_poster = movies ;
       mContext = context ;



    }

    @Override
    public int getCount() {
        return movie_poster.toArray().length;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie movie = getItem(position);

        ImageView moviesPoster;
        ImageView poster;

        // ViewHolder viewHolder ;

        if (convertView == null) {

            moviesPoster = new ImageView(mContext);

            moviesPoster.setScaleType(ImageView.ScaleType.CENTER_CROP);
            moviesPoster.setAdjustViewBounds(true);


        } else {
            moviesPoster = (ImageView) convertView;
            poster = (ImageView) convertView.findViewById(R.id.poster);
        }



        Movie url = getItem(position) ;



        Glide.with(mContext)
                .load(url.getmMovieposter())
                .error(R.drawable.installerposter)
                .crossFade()
                .into(moviesPoster) ;








              return moviesPoster ;
    }
}
