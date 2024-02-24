package ubr.persanal.movieapp.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ubr.persanal.movieapp.data.usecase.GetActorsUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetDetailActorUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetMovieDetailsUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetMoviesByActorUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetMoviesFavoriteUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetPoplarFilmsUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetTopRatedFilmsUseCaseImpl
import ubr.persanal.movieapp.data.usecase.GetUpComingFilmsUseCaseImpl
import ubr.persanal.movieapp.data.usecase.SetMovieFavoriteUseCaseIml
import ubr.persanal.movieapp.domain.usecase.GetActorsUseCase
import ubr.persanal.movieapp.domain.usecase.GetDetailActorUseCase
import ubr.persanal.movieapp.domain.usecase.GetMovieDetailsUseCase
import ubr.persanal.movieapp.domain.usecase.GetMoviesByActorUseCase
import ubr.persanal.movieapp.domain.usecase.GetMoviesFavoriteUseCase
import ubr.persanal.movieapp.domain.usecase.GetPoplarFilmsUseCase
import ubr.persanal.movieapp.domain.usecase.GetTopRatedFilmsUseCase
import ubr.persanal.movieapp.domain.usecase.GetUpComingFilmsUseCase
import ubr.persanal.movieapp.domain.usecase.SetMovieFavoriteUseCase


@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {


    @Binds
    fun provideGetActorsUseCase(useCase: GetActorsUseCaseImpl):GetActorsUseCase

    @Binds
    fun provideGetDetailActorUseCase(useCase: GetDetailActorUseCaseImpl):GetDetailActorUseCase

    @Binds
    fun provideGetMovieDetailsUseCase(useCase: GetMovieDetailsUseCaseImpl):GetMovieDetailsUseCase

    @Binds
    fun provideGetPoplarFilmsUseCase(useCase: GetPoplarFilmsUseCaseImpl):GetPoplarFilmsUseCase

    @Binds
    fun provideGetTopRatedFilmsUseCase(useCase: GetTopRatedFilmsUseCaseImpl):GetTopRatedFilmsUseCase


    @Binds
    fun provideGetMoviesFavoriteUseCase(useCase: GetMoviesFavoriteUseCaseImpl): GetMoviesFavoriteUseCase

    @Binds
    fun provideGetUpComingFilmsUseCase(useCase:GetUpComingFilmsUseCaseImpl): GetUpComingFilmsUseCase


    @Binds
    fun provideGetMoviesByActorUseCase(useCase: GetMoviesByActorUseCaseImpl): GetMoviesByActorUseCase


    @Binds
    fun provideSetMovieFavoriteUseCase(useCase: SetMovieFavoriteUseCaseIml): SetMovieFavoriteUseCase

}