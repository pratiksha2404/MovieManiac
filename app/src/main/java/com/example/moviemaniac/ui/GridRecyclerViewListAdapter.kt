package com.example.moviemaniac.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviemaniac.R
import com.example.moviemaniac.data.UpcomingMovies

class GridRecyclerViewListAdapter : ListAdapter<UpcomingMovies, GridRecyclerViewListAdapter.MyViewHolder>(DiffCallback())
{
    val TAG = "GridRecyclerViewListAdapter"
    class MyViewHolder(itemView: View) : ViewHolder(itemView)
    {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder
    {
        val itemView = LayoutInflater.from( parent.context ).inflate( R.layout.grid_item_layout, parent, false)
        return MyViewHolder(itemView)
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
            return oldItem.id != newItem.id
        }

        override fun areContentsTheSame(oldItem: UpcomingMovies, newItem: UpcomingMovies): Boolean
        {
            return oldItem == newItem
        }
    }
}