package com.example.moviemaniac.domain

import com.example.moviemaniac.data.MoviesDataRepository
import com.example.moviemaniac.data.UpcomingMoviesResponse
import javax.inject.Inject

class UpcomingMoviesUseCase @Inject constructor(private val moviesDataRepository: MoviesDataRepository)
{
    suspend fun execute( api_key: String, page: Int, lang: String ) : UpcomingMoviesResponse?
    {
        return moviesDataRepository.getUpcomingMovies( api_key, page, lang )
    }
}