package com.shareqube.popularmoviesapp;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
            // moviesPoster.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
            moviesPoster.setScaleType(ImageView.ScaleType.CENTER_CROP);
            moviesPoster.setAdjustViewBounds(true);
            // moviesPoster.setPadding(8, 8, 8, 8);

        } else {
            moviesPoster = (ImageView) convertView;
            poster = (ImageView) convertView.findViewById(R.id.poster);
        }

        //poster.setImageResource(mThumbIds[position]);

        Movie url = getItem(position) ;


        Log.e(LOG_TAG , "movie object dot poster"+ url.poster);



        Glide.with(mContext).load(url.poster).error(R.drawable.installerposter).into(moviesPoster) ;





            // return moviesPoster;
            // return  poster ;


              return moviesPoster ;
    }
}
