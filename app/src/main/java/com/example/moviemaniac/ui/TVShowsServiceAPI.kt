package com.example.moviemaniac.ui

import com.example.moviemaniac.data.PopularTVShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowsServiceAPI
{
    @GET("3/tv/popular")
    suspend fun getPopularTVShows(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ) : PopularTVShowResponse
}