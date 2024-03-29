package ubr.persanal.movieapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.source.MovieDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val  movieDataSource: MovieDataSource): MovieRepository {


    override suspend fun getMovieDetails(movieId: Long) = withContext(Dispatchers.IO){

        movieDataSource.getMovieDetails(movieId)
    }

    override suspend fun getPoplarFilms(page: Int) = withContext(Dispatchers.IO){

        movieDataSource.getPoplarFilms(page)
    }

    override suspend fun getTopRatedFilms(page:Int) = withContext(Dispatchers.IO){

        movieDataSource.getTopRatedFilms(page)
    }

    override suspend fun getFavoriteFilms(page: Int): Flow<ResourceUI<MoviePagingDto>> = withContext(Dispatchers.IO) {

        movieDataSource.getFavoriteFilms(page)
    }

    override suspend fun addFavorite(
        body: FavoriteRequestDto,
        itemDto: MoviePageItemDto
    ): Flow<ResourceUI<SuccessDto>>  = withContext(Dispatchers.IO){

        movieDataSource.addFavorite(body,itemDto)
    }


    override suspend fun getUpComingFilms(page:Int) = withContext(Dispatchers.IO){

        movieDataSource.getUpComingFilms(page)
    }

    override suspend fun getMoviesByActor(personId: Int): Flow<ResourceUI<MovieListByActorDto>>  = withContext(Dispatchers.IO){
        movieDataSource.getMoviesByActor(personId)
    }


}