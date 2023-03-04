package com.example.moviemaniac.di

import com.example.moviemaniac.data.MoviesDataRepository
import com.example.moviemaniac.data.TVShowRepository
import com.example.moviemaniac.domain.MoviesDetailsUseCase
import com.example.moviemaniac.domain.PopularTVShowsUseCase
import com.example.moviemaniac.domain.UpcomingMoviesUseCase
import com.example.moviemaniac.ui.MovieServiceAPI
import com.example.moviemaniac.ui.TVShowsServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMoviesDataRepository(movieServiceAPI: MovieServiceAPI):MoviesDataRepository{
        return MoviesDataRepository(movieServiceAPI)
    }

    @Provides
    fun provideUpcomingMoviesUseCase(moviesDataRepository: MoviesDataRepository):UpcomingMoviesUseCase{
        return UpcomingMoviesUseCase(moviesDataRepository)
    }

    @Provides
    fun provideMoviesDetailsUseCase(moviesDataRepository: MoviesDataRepository):MoviesDetailsUseCase{
        return MoviesDetailsUseCase(moviesDataRepository)
    }

    @Provides
    fun provideTVShowsRepository(tvShowsServiceAPI: TVShowsServiceAPI):TVShowRepository{
        return TVShowRepository(tvShowsServiceAPI)
    }

    @Provides
    fun providesPopularTVShowsUseCase(tvShowRepository: TVShowRepository):PopularTVShowsUseCase{
        return PopularTVShowsUseCase(tvShowRepository)
    }
}