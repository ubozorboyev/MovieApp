package ubr.persanal.movieapp.domain.reposotory

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.util.ResourceUI

interface MovieRepository {

    suspend fun getMovieDetails(movieId: Int):Flow<ResourceUI<MovieDetailsDto>>

    suspend fun getPoplarFilms(page:Int):Flow<ResourceUI<MoviePagingDto>>

    suspend fun getTopRatedFilms(page:Int):Flow<ResourceUI<MoviePagingDto>>

    suspend fun getUpComingFilms(page:Int):Flow<ResourceUI<MoviePagingDto>>

}