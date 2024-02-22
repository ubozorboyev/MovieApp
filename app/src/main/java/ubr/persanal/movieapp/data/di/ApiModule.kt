package ubr.persanal.movieapp.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ubr.persanal.onlineshop.data.api.ActorsApiService
import ubr.persanal.onlineshop.data.api.MoviesApiService


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {


    @Provides
    fun provideMoviesService(
        retrofit: Retrofit
    ): MoviesApiService = retrofit.create(MoviesApiService::class.java)


    @Provides
    fun provideActorsService(
        retrofit: Retrofit
    ): ActorsApiService = retrofit.create(ActorsApiService::class.java)


}