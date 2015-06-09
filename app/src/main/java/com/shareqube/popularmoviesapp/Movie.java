package com.shareqube.popularmoviesapp;

/**
 * Created by Jude Ben on 6/8/2015.
 */
public class Movie {


         final int id;
         final String title;
         final String poster ;
         final String releaseDate;
         final String overview ;
         final String user_rating ;


    String[] moviePoster ;





        public Movie(int id, String title, String poster ,String releaseDate, String overview, String user_rating) {
            this.id = id ;
            this.title = title ;
            this.poster = poster ;
            this.releaseDate = releaseDate ;
            this.overview = overview ;
            this.user_rating = user_rating ;


        }

    public void setMoviePoster(int size ,String url){

        moviePoster = new String[size] ;

        for(int i =0; i<size ; i++) {

            moviePoster[i] = url;
        }

    }

    public   String[] getMoviePoster(){

        return  moviePoster ;
    }
}

