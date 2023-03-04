package com.example.moviemaniac.data

data class PopularShowsData( val id: Int, val name: String, val overview: String,
                             val poster_path: String, val vote_average: Double,)

data class PopularTVShowResponse(
    var total_results: Int,
    var total_pages: Int,
    var page: Int,
    var results: List<PopularShowsData>
)