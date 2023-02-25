package com.example.moviemaniac.domain

import com.example.moviemaniac.data.MovieData
import com.example.moviemaniac.data.MoviesDataRepository

class MoviesDetailsUseCase( private val moviesDataRepository: MoviesDataRepository )
{
    suspend fun getMoviesDetails( api_key: String, id: Int, lang: String ) : MovieData
    {
        return moviesDataRepository.getMoviesDetails( api_key, id, lang)
    }
}