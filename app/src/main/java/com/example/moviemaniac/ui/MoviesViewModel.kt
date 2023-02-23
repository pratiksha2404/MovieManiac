package com.example.moviemaniac.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaniac.data.UpcomingMovies
import com.example.moviemaniac.domain.UpcomingMoviesUseCase
import kotlinx.coroutines.launch

class MoviesViewModel( private val upcomingMoviesUseCase: UpcomingMoviesUseCase ): ViewModel()
{
    private val upcomingMoviesMutable: MutableLiveData<List<UpcomingMovies>> = MutableLiveData()
    val upcomingMoviesLiveData: LiveData< List<UpcomingMovies>> = upcomingMoviesMutable
    fun getUpcomingMovies( api_key: String, page: Int, lang: String )
    {
        viewModelScope.launch {
            val response = upcomingMoviesUseCase.execute( api_key, page, lang )
            Log.d("MovieViewModel", "getUpcomingMovies: Response = $response" )
            upcomingMoviesMutable.postValue( response.results )

        }
    }
}