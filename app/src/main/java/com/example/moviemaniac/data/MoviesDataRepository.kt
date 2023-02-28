package com.example.moviemaniac.data

import android.util.Log
import com.example.moviemaniac.ui.MovieServiceAPI
import javax.inject.Inject

class MoviesDataRepository @Inject constructor( private val movieServiceAPI: MovieServiceAPI )
{
    suspend fun getUpcomingMovies( api_key: String, page: Int, lang: String) : UpcomingMoviesResponse
    {

        val response = movieServiceAPI.getUpcomingMovies( api_key, page, lang )
        Log.d( "MovieRepo", "getUpcomingMovies: response = " + response )

        return response
//        call.enqueue(object : Callback<UpcomingMoviesResponse>
//                     {
//                         override fun onResponse(
//                             call: Call<UpcomingMoviesResponse>,
//                             response: Response<UpcomingMoviesResponse>
//                         )
//                         {
//                             Log.d("MovieRepo", "onResponse: = " + response.body() )
//                             onResponseListener.onSuccess( response.body() )
//                         }
//
//                         override fun onFailure(
//                             call: Call<UpcomingMoviesResponse>,
//                             t: Throwable
//                         )
//                         {
//                             onResponseListener.onFailure( 400)
//                         }
//
//                     })
    }

    suspend fun getMoviesDetails(apiKey: String, id: Int, lang: String): MovieData
    {
        val response = movieServiceAPI.getMovieDetails( id, apiKey, lang )
        Log.d("MoviesRepo", "getMoviesDetails: response = $response" )
        return response
    }
}