package com.example.moviemaniac.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemaniac.data.PopularShowsData
import com.example.moviemaniac.domain.PopularTVShowsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor( private val tvShowsUseCase: PopularTVShowsUseCase ) : ViewModel()
{
    private val TAG = "TVShowsViewModel"
    private val _popularTVShowsData = MutableLiveData<List<PopularShowsData>>()
    val popularShowsData: LiveData<List<PopularShowsData>> = _popularTVShowsData
    init
    {
        viewModelScope.launch {
            Log.d(TAG, "null() called")
            val response = tvShowsUseCase.execute("751260fc614a6e20c18c6870ad9c6ca8", "en-US", 1)
            Log.d(TAG, "response: $response")

            _popularTVShowsData.postValue(response.results)
        }
    }
}