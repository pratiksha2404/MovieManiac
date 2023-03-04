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
import com.example.moviemaniac.databinding.FragmentPopularTvShowsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowsFragment : Fragment()
{
    private val TAG = "TVShowsFragment"
    private lateinit var binding: FragmentPopularTvShowsBinding
    private val tvShowsViewModel: TVShowsViewModel by activityViewModels()
    private var adapter: GridHorizontalRecyclerViewAdapter = GridHorizontalRecyclerViewAdapter()
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        binding = FragmentPopularTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        binding.horizontalRecyclerview.adapter = adapter
        layoutManager = GridLayoutManager(view.context, 1, RecyclerView.HORIZONTAL, false)
        binding.horizontalRecyclerview.layoutManager = layoutManager
        tvShowsViewModel.popularShowsData.observe( viewLifecycleOwner ){
            Log.d(TAG, "onViewCreated: response = $it")
            adapter.submitList( it.toMutableList())
        }
    }
}