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
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.moviemaniac.R
import com.example.moviemaniac.data.UpcomingMoviesRepository
import com.example.moviemaniac.domain.UpcomingMoviesUseCase


/**
 * A simple [Fragment] subclass.
 * Use the [HomePage.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomePage : Fragment()
{
    private val TAG = "MainActivity"
    private val apiKey = "751260fc614a6e20c18c6870ad9c6ca8"
    private val upcomingMoviesRepository: UpcomingMoviesRepository = UpcomingMoviesRepository()
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase = UpcomingMoviesUseCase(upcomingMoviesRepository)
    private lateinit var recyclerView: RecyclerView
    private var adapter: GridRecyclerViewListAdapter = GridRecyclerViewListAdapter()
    private lateinit var manager:GridLayoutManager

    private var isLoading = true
    private var pageNumber = 0

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

    private fun initScrollListener()
    {
        recyclerView.addOnScrollListener(object: OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int)
            {
                super.onScrolled(recyclerView, dx, dy)

                Log.d(TAG, "onScrolled: gridlayoutManagerPosition = ${manager.findFirstCompletelyVisibleItemPosition()}")
                Log.d(TAG, "onScrolled: gridlayoutManagerPosition = ${manager.findLastCompletelyVisibleItemPosition()}")
                Log.d(TAG, "onScrolled: gridlayoutManagerPosition = ${manager.findLastVisibleItemPosition()}")
                Log.d(TAG, "onScrolled: upcomingMovies size = ${adapter.currentList.size}")
                if(!isLoading)
                {
                    if(manager.findLastCompletelyVisibleItemPosition() == adapter.currentList.size - 1
                        && manager.findFirstCompletelyVisibleItemPosition() != 0)
                    {
                        Log.d(TAG, "onScrolled: ")
                        isLoading = true
                        manager.scrollToPosition(adapter.currentList.size)
                        loadMoreData()
                    }
                }
            }
        })
    }

    private fun loadMoreData()
    {
        pageNumber++
        Log.d(TAG, "loadMoreData: PageNumber = $pageNumber" )
        movieViewModel.getUpcomingMovies(apiKey, pageNumber, "en-US" )
        isLoading = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        loadMoreData()
        movieViewModel.upcomingMoviesLiveData.observe( viewLifecycleOwner ){
            Log.d(TAG, "observe: upcomingMoviesLiveData ")
            adapter.submitList(it.toMutableList())
        }

        manager = GridLayoutManager(view.context, 4)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager

        initScrollListener()
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