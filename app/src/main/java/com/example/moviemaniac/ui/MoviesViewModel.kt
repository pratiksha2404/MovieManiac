package com.example.moviemaniac.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaniac.data.MovieData
import com.example.moviemaniac.data.UpcomingMovies
import com.example.moviemaniac.domain.MoviesDetailsUseCase
import com.example.moviemaniac.domain.UpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase,
    private val moviesDetailsUseCase: MoviesDetailsUseCase
) : ViewModel()
{
    private val upcomingMoviesMutable: MutableLiveData<List<UpcomingMovies>> = MutableLiveData()
    val upcomingMoviesLiveData: LiveData< List<UpcomingMovies>> = upcomingMoviesMutable
    private val movieDetailsMutableLiveData: MutableLiveData<MovieData> = MutableLiveData()
    val moviesDetailsLiveData: LiveData< MovieData > = movieDetailsMutableLiveData
    private val TAG = "MoviesViewModel"
    fun getUpcomingMovies( api_key: String, page: Int, lang: String )
    {
        Log.d(TAG, "getUpcomingMovies() called with: api_key = $api_key, page = $page, lang = $lang")
        viewModelScope.launch {
            val response = upcomingMoviesUseCase.execute( api_key, page, lang )
            Log.d("MovieViewModel", "getUpcomingMovies: Response = $response" )
            upcomingMoviesMutable.postValue( response.results )

        }
    }

    fun getMovieDetails( api_key: String, id: Int, lang: String )
    {
        Log.d(TAG, "getMovieDetails() called with: api_key = $api_key, id = $id, lang = $lang")
        viewModelScope.launch {
            val response = moviesDetailsUseCase.getMoviesDetails( api_key, id, lang)
            Log.d(TAG, "getMovieDetails: response = $response" )
            movieDetailsMutableLiveData.postValue( response )
        }
    }
}