package ubr.persanal.movieapp.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.source.MovieDataSource
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.onlineshop.data.api.MoviesApiService
import java.lang.Exception
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val moviesApiService: MoviesApiService
) : MovieDataSource {

    override suspend fun getUpComingFilms(page: Int): Flow<ResourceUI<MoviePagingDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.getUpComing(BuildConfig.API_KEY, page)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun getTopRatedFilms(page: Int): Flow<ResourceUI<MoviePagingDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.getTopRated(BuildConfig.API_KEY,page)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            e.printStackTrace()
        }


    }

    override suspend fun getFavoriteFilms(page: Int): Flow<ResourceUI<MoviePagingDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.getFavoriteMovies(BuildConfig.ACCOUNT_ID.toInt(),BuildConfig.API_KEY,BuildConfig.SESSION_ID,page)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    override suspend fun getPoplarFilms(page: Int): Flow<ResourceUI<MoviePagingDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.getPopular(BuildConfig.API_KEY,page)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            e.printStackTrace()
        }
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<ResourceUI<MovieDetailsDto>>  = flow{

        emit(ResourceUI.Loading)

        try {
            val response = moviesApiService.getMovieDetails(movieId, BuildConfig.API_KEY)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))

            else emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            emit(ResourceUI.Error(e.message))

            e.printStackTrace()
        }
    }

    override suspend fun getMoviesByActor(personId: Int): Flow<ResourceUI<MovieListByActorDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.getMoviesByActor(personId, BuildConfig.API_KEY)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))

            else emit(ResourceUI.Error(response.message()))


        } catch (e: Exception) {

            e.printStackTrace()
        }

    }


}