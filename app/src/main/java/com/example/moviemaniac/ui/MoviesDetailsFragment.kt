package com.example.moviemaniac.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.moviemaniac.data.Genres
import com.example.moviemaniac.data.MovieData
import com.example.moviemaniac.data.MoviesDataRepository
import com.example.moviemaniac.databinding.FragmentMoviesDetailsBinding
import com.example.moviemaniac.domain.MoviesDetailsUseCase
import com.example.moviemaniac.domain.UpcomingMoviesUseCase

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MoviesDetailsFragment : Fragment()
{
    private lateinit var binding: FragmentMoviesDetailsBinding
    private var id: Int? = null
    private var TAG = "MoviesDetailsFragment"
    private val apiKey = "751260fc614a6e20c18c6870ad9c6ca8"
    private var moviesDataRepository = MoviesDataRepository()
    private var upcomingMoviesUseCase = UpcomingMoviesUseCase(moviesDataRepository)
    private var moviesDetailsUseCase = MoviesDetailsUseCase(moviesDataRepository)
    private lateinit var movieData: MovieData

    private val moviesViewModel: MoviesViewModel by activityViewModels {
        MovieViewModelFactory( upcomingMoviesUseCase, moviesDetailsUseCase )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        binding = FragmentMoviesDetailsBinding.inflate(inflater,container,false)
        id = arguments?.getInt("id")
        Log.d(TAG, "onCreateView: id = $id" )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        id?.let { moviesViewModel.getMovieDetails(apiKey, it, "en-US") }
        moviesViewModel.moviesDetailsLiveData.observe( viewLifecycleOwner ) {
            Log.d(TAG, "onViewCreated: reponse = $it")
            movieData = it
            Log.d(TAG, "onViewCreated: reponse = $movieData")

            val albumArtUrl = "https://image.tmdb.org/t/p/w500" + movieData.poster_path
            Glide.with(view.context).load(albumArtUrl).into(binding.albumart)
            binding.title.setText(movieData.name)
            binding.overview.setText(movieData.overview)
            var genres = movieData.genres[0].name
            for( i in 1 until movieData.genres.size )
            {
                val genre = movieData.genres[i].name
                genres = "$genres, $genre"
            }
            binding.genres.setText(genres)
            binding.title.setText( movieData.title )
            binding.Period.setText( movieData.release_date )
            binding.ratingBar.numStars = 5
            binding.ratingBar.rating = ((movieData.vote_average / 2).toFloat())
        }
    }
}