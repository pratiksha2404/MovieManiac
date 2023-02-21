package com.example.moviemaniac.ui

import androidx.lifecycle.ViewModel
import com.example.moviemaniac.domain.UpcomingMoviesUseCase

class MoviesViewModel( private val upcomingMoviesUseCase: UpcomingMoviesUseCase ): ViewModel()
{
    fun getUpcomingMovies( api_key: String, page: Int, lang: String, onResponseListener: OnResponseListener )
    {
        upcomingMoviesUseCase.execute( api_key, page, lang, onResponseListener)
    }
}