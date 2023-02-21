package com.example.moviemaniac.ui

import com.example.moviemaniac.data.UpcomingMoviesResponse

interface OnResponseListener
{
    fun onSuccess( response: UpcomingMoviesResponse? )
    fun onFailure( errorCode: Int )
}