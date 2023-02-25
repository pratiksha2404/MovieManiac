package com.example.moviemaniac.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviemaniac.data.UpcomingMovies
import com.example.moviemaniac.databinding.GridItemLayoutBinding

class GridRecyclerViewListAdapter : ListAdapter<UpcomingMovies, GridRecyclerViewListAdapter.MyViewHolder>(DiffCallback())
{
    private val TAG = "GridRecyclerViewListAdapter"
    private lateinit var binding: GridItemLayoutBinding
    class MyViewHolder(itemView: GridItemLayoutBinding) : ViewHolder(itemView.root)
    {
        val imageView = itemView.imageView
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder
    {
        val inflater = LayoutInflater.from( parent.context )
        binding = GridItemLayoutBinding.inflate(inflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val albumArtUrl = "https://image.tmdb.org/t/p/w500" + currentList[position].poster_path
        Log.d(TAG, "onBindViewHolder: $position = $albumArtUrl")
        Glide.with(holder.itemView).load(albumArtUrl).into(holder.imageView)
    }

    class DiffCallback : DiffUtil.ItemCallback<UpcomingMovies>(){

        override fun areItemsTheSame(oldItem: UpcomingMovies, newItem: UpcomingMovies): Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UpcomingMovies, newItem: UpcomingMovies): Boolean
        {
            return oldItem == newItem
        }
    }

    override fun submitList(list: MutableList<UpcomingMovies>?)
    {
        val upComingMoviesList = mutableListOf<UpcomingMovies>()

        if( currentList.size !=0)
        {
            upComingMoviesList.addAll(currentList)
        }
        if (list != null)
        {
            upComingMoviesList.addAll(currentList.size, list)
        }
        Log.d(TAG, "submitList: list size = $list")
        super.submitList(upComingMoviesList)
    }

}