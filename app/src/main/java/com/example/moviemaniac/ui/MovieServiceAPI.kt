package com.example.moviemaniac.ui

import com.example.moviemaniac.data.UpcomingMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieServiceAPI
{
    @GET("3/movie/upcoming")
    suspend fun getUpcomingMovies( @Query("api_key") api_key: String,
                           @Query("page") page: Int,
                           @Query("language") language:String ):UpcomingMoviesResponse
}