package com.example.moviemaniac.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.moviemaniac.R
import com.example.moviemaniac.data.UpcomingMoviesRepository
import com.example.moviemaniac.data.UpcomingMoviesResponse
import com.example.moviemaniac.domain.UpcomingMoviesUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnResponseListener
{
    val TAG = "MainActivity"
    private val api_key = "751260fc614a6e20c18c6870ad9c6ca8"
    private val upcomingMoviesRepository: UpcomingMoviesRepository = UpcomingMoviesRepository()
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase = UpcomingMoviesUseCase(upcomingMoviesRepository)

    private val movieViewModel: MoviesViewModel by viewModels {
        MovieViewModelFactory( upcomingMoviesUseCase )
    }
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieViewModel.getUpcomingMovies( api_key, 1, "en-US",this )
    }

    override fun onSuccess(response: UpcomingMoviesResponse?)
    {
        Log.d(TAG, "onSuccess: response = $response")
    }

    override fun onFailure(errorCode: Int)
    {
        Log.d(TAG, "onFailure: $errorCode")
    }

    class MovieViewModelFactory( private val upcomingMoviesUseCase: UpcomingMoviesUseCase ) : ViewModelProvider.Factory
    {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T
        {
            return MoviesViewModel(upcomingMoviesUseCase) as T
        }
    }
}
