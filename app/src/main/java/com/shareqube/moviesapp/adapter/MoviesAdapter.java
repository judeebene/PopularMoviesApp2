package com.shareqube.moviesapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.shareqube.moviesapp.Movie;
import com.shareqube.moviesapp.MovieDiscoveryFragment;
import com.shareqube.moviesapp.R;
import com.shareqube.moviesapp.data.MovieContract;


import java.io.File;
import java.util.List;

/**
 * Created by Jude Ben on 6/7/2015.
 */
public class MoviesAdapter extends CursorAdapter {

    static Context mContext;
    String LOG_TAG = MoviesAdapter.class.getSimpleName();
    List<Movie> movie_poster;
    List<String> path;


    int resouse_id;

    LayoutInflater minflater;
    ViewHolder holder;


    // ignoring   defaut text view id with super
    public MoviesAdapter(Context context, Cursor c, int flag) {
        super(context, c, flag);


        minflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        mContext = context;

    }




    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);

        holder = new ViewHolder(view);

        view.setTag(holder);

        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        holder = (ViewHolder) view.getTag();

        holder.moviesPoster.setAdjustViewBounds(true);


        int position = cursor.getPosition();

        String poster = cursor.getColumnName(3);

        String url = cursor.getString(cursor.getColumnIndex(poster));


        Glide.with(mContext).load(url).error(R.drawable.no_poster).into(holder.moviesPoster);
        //new DownloadPosterToCacheTask().execute(url);



       /*     // start to get from cache

            Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    Log.i(LOG_TAG, "Listener onException: " + e.toString());
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    Log.e(LOG_TAG, "onResourceReady with resource = " + resource);
                    Log.i(LOG_TAG, "onResourceReady from memory cache = " + isFromMemoryCache);
                    return false;
                }
            }).error(R.drawable.no_poster)
                    .into(new SimpleTarget<GlideDrawable>(256, 256) {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            Log.i(LOG_TAG, "GlideDrawalble = '" + resource + "'");
                            holder.moviesPoster.setImageDrawable(resource.getCurrent());
                        }
                    });

            // end from cache

*/
    }

    static class ViewHolder {


        ImageView moviesPoster;

        public ViewHolder(View v) {
            moviesPoster = (ImageView) v.findViewById(R.id.poster);
        }


    }


    private class DownloadPosterToCacheTask extends AsyncTask<String, Void, File> {
        @Override
        protected File doInBackground(String... params) {
            FutureTarget<File> future = Glide.with(mContext)
                    .load(params[0])
                    .downloadOnly(256, 256);

            File file = null;
            try {
                file = future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return file;
        }
    }
}
