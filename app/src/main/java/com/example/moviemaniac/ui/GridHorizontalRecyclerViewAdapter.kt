package com.example.moviemaniac.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviemaniac.data.PopularShowsData
import com.example.moviemaniac.databinding.HorizontalGridItemLayoutBinding

class GridHorizontalRecyclerViewAdapter : ListAdapter< PopularShowsData, GridHorizontalRecyclerViewAdapter.MyViewHolder>(DiffCallback())
{
    private lateinit var binding: HorizontalGridItemLayoutBinding
    private val TAG = "GridHorizontalRecyclerViewAdapter"
    class MyViewHolder(itemView: HorizontalGridItemLayoutBinding) : ViewHolder(itemView.root)
    {
        val imageView = itemView.imageview
    }

    class DiffCallback : DiffUtil.ItemCallback<PopularShowsData>()
    {
        override fun areItemsTheSame(oldItem: PopularShowsData, newItem: PopularShowsData): Boolean
        {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularShowsData,
            newItem: PopularShowsData
        ): Boolean
        {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val inflater = LayoutInflater.from( parent.context )
        binding = HorizontalGridItemLayoutBinding.inflate(inflater,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        val albumArtUrl = "https://image.tmdb.org/t/p/w500" + currentList[position].poster_path
        Log.d(TAG, "onBindViewHolder: $position = $albumArtUrl")
        Glide.with(holder.itemView).load(albumArtUrl).into(holder.imageView)
    }
}