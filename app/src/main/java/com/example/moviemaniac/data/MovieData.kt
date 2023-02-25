package com.example.moviemaniac.data

data class MovieData( val id: Int, val name: String, val backdrop_path: String,
                      val popularity: Double, val poster_path: String, val overview: String,
                      val status: String, val productionCompanies: ArrayList<ProductionCompanies>,
                      val video: Boolean, val productionCountries: ArrayList<ProductionCountries>,
                      val genres: ArrayList<Genres>, )
