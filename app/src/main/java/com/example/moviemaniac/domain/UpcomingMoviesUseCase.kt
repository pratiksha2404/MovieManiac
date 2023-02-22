package com.example.moviemaniac.domain

import com.example.moviemaniac.data.UpcomingMovies
import com.example.moviemaniac.data.UpcomingMoviesRepository
import com.example.moviemaniac.data.UpcomingMoviesResponse
import com.example.moviemaniac.ui.OnResponseListener

class UpcomingMoviesUseCase(private val upcomingMoviesRepository: UpcomingMoviesRepository)
{
    suspend fun execute( api_key: String, page: Int, lang: String ) : UpcomingMoviesResponse
    {
        return upcomingMoviesRepository.getUpcomingMovies(api_key, page, lang )
    }
}