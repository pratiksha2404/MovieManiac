package com.example.moviemaniac.di

import com.example.moviemaniac.ui.MovieServiceAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule
{
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAPIService(retrofit: Retrofit):MovieServiceAPI{
        return retrofit.create(MovieServiceAPI::class.java)
    }


    @Provides
    fun provideOkHttpClient(): OkHttpClient
    {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpBuilder = OkHttpClient.Builder()
        httpBuilder.addInterceptor(interceptor)
        return httpBuilder.build()
    }
}