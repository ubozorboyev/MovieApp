package ubr.persanal.movieapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ubr.persanal.movieapp.data.source.ActorDataSourceImpl
import ubr.persanal.movieapp.data.source.MovieDataSourceImpl
import ubr.persanal.movieapp.domain.source.ActorDataSource
import ubr.persanal.movieapp.domain.source.MovieDataSource


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {


    @Binds
    fun provideActorDataSource(actorDataSource: ActorDataSourceImpl):ActorDataSource


    @Binds
    fun provideMovieDataSource(movieDataSource: MovieDataSourceImpl):MovieDataSource

}