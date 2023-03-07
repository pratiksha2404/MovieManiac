package com.example.moviemaniac.data

import com.example.moviemaniac.ui.TVShowsServiceAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TVShowRepository @Inject constructor( private val tvShowsServiceAPI: TVShowsServiceAPI )
{
    suspend fun getPopularTVShows(apiKey: String, language: String, page: Int): PopularTVShowResponse? = withContext(Dispatchers.IO)
    {
        try
        {
            return@withContext tvShowsServiceAPI.getPopularTVShows(apiKey, language, page)
        } catch (e: Exception)
        {
            e.printStackTrace()
            return@withContext null
        }
    }
}