package com.example.moviemaniac.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.moviemaniac.R
import com.example.moviemaniac.data.UpcomingMovies

class GridRecyclerViewAdapter( private val upcomingMovies: List<UpcomingMovies> ): RecyclerView.Adapter<GridRecyclerViewAdapter.MyViewHolder>()
{
    class MyViewHolder(itemView: View): ViewHolder(itemView)
    {
        var imageView:ImageView
        val view = itemView

        init
        {
            imageView = itemView.findViewById( R.id.imageView )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val itemView = LayoutInflater.from( parent.context ).inflate( R.layout.grid_item_layout, parent, false )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int
    {
        return upcomingMovies.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
        Log.d("Adapter", "onBindViewHolder: position = $position")
        val albumArtUrl = "https://image.tmdb.org/t/p/w500" + upcomingMovies[position].poster_path
        Log.d("Adapter", "onBindViewHolder: album art = $albumArtUrl")
        Glide.with(holder.view.context).load(albumArtUrl).into(holder.imageView)
    }

}