package com.example.moviemaniac.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.moviemaniac.R
import com.example.moviemaniac.data.MoviesDataRepository
import com.example.moviemaniac.databinding.FragmentHomePageBinding
import com.example.moviemaniac.domain.MoviesDetailsUseCase
import com.example.moviemaniac.domain.UpcomingMoviesUseCase


class HomePage : Fragment(), OnItemClickListener
{
    private val TAG = "MainActivity"
    private val apiKey = "751260fc614a6e20c18c6870ad9c6ca8"
    private val moviesDataRepository: MoviesDataRepository = MoviesDataRepository()
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase = UpcomingMoviesUseCase(moviesDataRepository)
    private var adapter: GridRecyclerViewListAdapter = GridRecyclerViewListAdapter( this )
    private lateinit var manager:GridLayoutManager

    private val moviesUseCase = MoviesDetailsUseCase(moviesDataRepository)

    private var isLoading = true
    private var pageNumber = 0
    private lateinit var binding: FragmentHomePageBinding

    private val movieViewModel: MoviesViewModel by activityViewModels {
        MovieViewModelFactory(upcomingMoviesUseCase, moviesUseCase )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentHomePageBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    private fun initScrollListener()
    {
        binding.recyclerView.addOnScrollListener(object: OnScrollListener(){
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

        loadMoreData()
        movieViewModel.upcomingMoviesLiveData.observe( viewLifecycleOwner ){
            Log.d(TAG, "observe: upcomingMoviesLiveData ")
            adapter.submitList(it.toMutableList())
        }

        manager = GridLayoutManager(view.context, 4)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = manager

        initScrollListener()
    }
    override fun onItemClick(id: Int )
    {
        Log.d(TAG, "onItemClick: id = $id" )
        val bundle = Bundle()
        bundle.putInt("id", id )
        val fragment = MoviesDetailsFragment()
        fragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)?.addToBackStack("MoviesDetails")?.commit()
    }
}