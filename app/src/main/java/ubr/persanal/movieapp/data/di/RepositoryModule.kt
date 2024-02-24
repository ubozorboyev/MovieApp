package ubr.persanal.movieapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ubr.persanal.movieapp.data.local.FavoriteDao
import ubr.persanal.movieapp.data.repository.ActorRepositoryImpl
import ubr.persanal.movieapp.data.repository.MovieRepositoryImpl
import ubr.persanal.movieapp.domain.reposotory.ActorRepository
import ubr.persanal.movieapp.domain.reposotory.MovieRepository


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @Binds
    fun provideMovieRepository( movieRepository: MovieRepositoryImpl): MovieRepository

    @Binds
    fun provideActorRepository(actorRepository: ActorRepositoryImpl): ActorRepository




}