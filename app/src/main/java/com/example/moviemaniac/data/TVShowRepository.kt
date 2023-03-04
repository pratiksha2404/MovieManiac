package com.example.moviemaniac.data

import com.example.moviemaniac.ui.TVShowsServiceAPI
import javax.inject.Inject

class TVShowRepository @Inject constructor( private val tvShowsServiceAPI: TVShowsServiceAPI )
{
    suspend fun getPopularTVShows(apiKey: String, language: String, page: Int):PopularTVShowResponse
    {
        return tvShowsServiceAPI.getPopularTVShows(apiKey,language,page)
    }

}