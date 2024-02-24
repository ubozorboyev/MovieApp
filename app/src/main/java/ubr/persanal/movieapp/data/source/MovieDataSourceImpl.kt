package ubr.persanal.movieapp.data.source

import android.content.Context
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.data.local.FavoriteDao
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.domain.source.MovieDataSource
import ubr.persanal.movieapp.util.BitmapConverter
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.onlineshop.data.api.MoviesApiService
import java.lang.Exception
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moviesApiService: MoviesApiService,
    private val favoriteDao: FavoriteDao,
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


            if (response.isSuccessful){
//
               response.body()?.let {pageResponse->

                   val listIds = favoriteDao.getIDsFromFavoriteList()

                   listIds.forEach {
                       if (!pageResponse.results.map { it.id }.contains(it)){
                           favoriteDao.deleteFavoriteMovie(it)
                       }

                   }

//                   pageResponse.results.forEach {
//
//                       if (!listIds.contains(it.id)){
//
//                           withContext(Dispatchers.IO){
//
//                               val bitmap = Glide.with(context)
//                                   .asBitmap()
//                                   .load(BuildConfig.IMAGE_URL + it.backdrop_path)
//                                   .submit()
//                                   .get()
//
//                               val entity = it.toEntity()
//
//                               entity.imageString = BitmapConverter.converterBitmapToString(bitmap)
//
//                               favoriteDao.addFavoriteMovie(entity)
//
//                           }
//
//
//                       }
//
//                   }

                }

                val list = favoriteDao.getFavoriteMovies(10,page)

                val moviePageItemDto = MoviePagingDto(page, list.map { it.toDto() }, page, list.size)

                emit(ResourceUI.Resource(moviePageItemDto))


            }
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

    override suspend fun getMovieDetails(movieId: Long): Flow<ResourceUI<MovieDetailsDto>>  = flow{

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

    override suspend fun addFavorite(body: FavoriteRequestDto, itemDto: MoviePageItemDto): Flow<ResourceUI<SuccessDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.addFavoriteMovies(BuildConfig.ACCOUNT_ID.toInt(), BuildConfig.API_KEY, BuildConfig.SESSION_ID, body.toRequest())

            if (response.isSuccessful){

                withContext(Dispatchers.IO){

                    val bitmap = Glide.with(context)
                        .asBitmap()

                        .load(BuildConfig.IMAGE_URL + itemDto.backdrop_path)
                        .submit()
                        .get()

                    val entity = itemDto.toEntity()

                    entity.imageString = BitmapConverter.converterBitmapToString(bitmap)


                    favoriteDao.addFavoriteMovie(entity)

                }


                emit(ResourceUI.Resource(response.body()?.toDto()))


            }

            else emit(ResourceUI.Error(response.message()))


        } catch (e: Exception) {

            e.printStackTrace()
        }
    }


}