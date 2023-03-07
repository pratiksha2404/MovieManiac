package com.example.moviemaniac.domain

import com.example.moviemaniac.data.PopularTVShowResponse
import com.example.moviemaniac.data.TVShowRepository
import javax.inject.Inject

class PopularTVShowsUseCase @Inject constructor( private val tvShowRepository: TVShowRepository )
{
    suspend fun execute( api_key: String, language: String, page: Int) : PopularTVShowResponse?
    {
        return tvShowRepository.getPopularTVShows( api_key, language, page)
    }
}