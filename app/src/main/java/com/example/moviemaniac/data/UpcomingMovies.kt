package com.example.moviemaniac.data

data class UpcomingMovies( var id: Int, var title: String, var release_date: String,
                           var poster_path: String )

data class UpcomingMoviesResponse(
    var total_results: Int,
    var total_pages: Int,
    var page: Int,
    var results: List<UpcomingMovies>
)
