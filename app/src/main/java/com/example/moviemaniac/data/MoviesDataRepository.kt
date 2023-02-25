package com.example.moviemaniac.data

import android.util.Log
import com.example.moviemaniac.ui.MovieServiceAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesDataRepository
{
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieServiceAPI = retrofit.create(MovieServiceAPI::class.java)

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
        var response = movieServiceAPI.getMovieDetails( id, apiKey, lang )
        Log.d("MoviesRepo", "getMoviesDetails: response = $response" )
        return response
    }
}