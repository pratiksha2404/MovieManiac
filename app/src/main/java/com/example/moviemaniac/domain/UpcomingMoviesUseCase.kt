package com.example.moviemaniac.domain

import com.example.moviemaniac.data.UpcomingMovies
import com.example.moviemaniac.data.UpcomingMoviesRepository
import com.example.moviemaniac.ui.OnResponseListener

class UpcomingMoviesUseCase(private val upcomingMoviesRepository: UpcomingMoviesRepository)
{
    fun execute( api_key: String, page: Int, lang: String, onResponseListener: OnResponseListener )
    {
        upcomingMoviesRepository.getUpcomingMovies(api_key, page, lang, onResponseListener)
    }
}