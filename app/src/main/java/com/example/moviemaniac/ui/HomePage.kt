package com.example.moviemaniac.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemaniac.R
import com.example.moviemaniac.data.UpcomingMovies
import com.example.moviemaniac.data.UpcomingMoviesRepository
import com.example.moviemaniac.domain.UpcomingMoviesUseCase


/**
 * A simple [Fragment] subclass.
 * Use the [HomePage.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePage : Fragment()
{
    val TAG = "MainActivity"
    private val api_key = "751260fc614a6e20c18c6870ad9c6ca8"
    private val upcomingMoviesRepository: UpcomingMoviesRepository = UpcomingMoviesRepository()
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase = UpcomingMoviesUseCase(upcomingMoviesRepository)
    private lateinit var recyclerView: RecyclerView
    private var adapter: GridRecyclerViewListAdapter = GridRecyclerViewListAdapter()

    private val movieViewModel: MoviesViewModel by viewModels {
        MovieViewModelFactory(upcomingMoviesUseCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)
        movieViewModel.getUpcomingMovies( api_key, 1, "en-US" )
        movieViewModel.upcomingMoviesLiveData.observe( viewLifecycleOwner ){
            Log.d(TAG, "observe: upcomingMoviesLiveData ")
            adapter.submitList(it)
        }

        val manager = GridLayoutManager(view.context, 3)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
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