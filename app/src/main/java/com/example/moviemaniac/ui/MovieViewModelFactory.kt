package com.example.moviemaniac.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviemaniac.domain.MoviesDetailsUseCase
import com.example.moviemaniac.domain.UpcomingMoviesUseCase

class MovieViewModelFactory(private val upcomingMoviesUseCase: UpcomingMoviesUseCase,
                            private val moviesUseCase: MoviesDetailsUseCase ) : ViewModelProvider.Factory
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        return MoviesViewModel(upcomingMoviesUseCase, moviesUseCase ) as T
    }
}