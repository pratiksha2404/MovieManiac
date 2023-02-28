package com.example.moviemaniac.di

import com.example.moviemaniac.ui.MovieServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
         return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideAPIService(retrofit: Retrofit):MovieServiceAPI{
        return retrofit.create(MovieServiceAPI::class.java)
    }
}