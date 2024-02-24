package ubr.persanal.movieapp.domain.source

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.data.model.FavoriteRequest
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.util.ResourceUI

interface MovieDataSource {

    suspend fun getUpComingFilms(page:Int): Flow<ResourceUI<MoviePagingDto>>

    suspend fun getTopRatedFilms(page:Int):Flow<ResourceUI<MoviePagingDto>>

    suspend fun getFavoriteFilms(page:Int):Flow<ResourceUI<MoviePagingDto>>

    suspend fun getPoplarFilms(page:Int):Flow<ResourceUI<MoviePagingDto>>

    suspend fun getMovieDetails(movieId: Long):Flow<ResourceUI<MovieDetailsDto>>

    suspend fun getMoviesByActor(personId: Int): Flow<ResourceUI<MovieListByActorDto>>

    suspend fun addFavorite(body: FavoriteRequestDto, itemDto: MoviePageItemDto):Flow<ResourceUI<SuccessDto>>


}