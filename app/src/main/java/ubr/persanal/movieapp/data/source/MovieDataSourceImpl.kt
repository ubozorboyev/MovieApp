package ubr.persanal.movieapp.data.source

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.R
import ubr.persanal.movieapp.data.local.FavoriteDao
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.domain.source.MovieDataSource
import ubr.persanal.movieapp.util.extentions.isNetworkAvailable
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

            val listIds = favoriteDao.getIDsFromFavoriteList()


            val response = moviesApiService.getUpComing(BuildConfig.API_KEY, page)

            if (response.isSuccessful){

                response.body()?.results?.forEach {

                    it.is_favorote = listIds.contains(it.id)

                }

                emit(ResourceUI.Resource(response.body()?.toDto()))

            }
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            if (!context.isNetworkAvailable()) {

                emit(ResourceUI.Error(context.getString(R.string.no_access_network)))
            }
            e.printStackTrace()
        }

    }

    override suspend fun getTopRatedFilms(page: Int): Flow<ResourceUI<MoviePagingDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val listIds = favoriteDao.getIDsFromFavoriteList()

            val response = moviesApiService.getTopRated(BuildConfig.API_KEY,page)

            if (response.isSuccessful){

                response.body()?.results?.forEach {

                    it.is_favorote = listIds.contains(it.id)

                }

                emit(ResourceUI.Resource(response.body()?.toDto()))

            }
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            if (!context.isNetworkAvailable()) {

                emit(ResourceUI.Error(context.getString(R.string.no_access_network)))
            }
            e.printStackTrace()
        }


    }

    override suspend fun getFavoriteFilms(page: Int): Flow<ResourceUI<MoviePagingDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val response = moviesApiService.getFavoriteMovies(
                BuildConfig.ACCOUNT_ID.toInt(), BuildConfig.API_KEY, BuildConfig.SESSION_ID, page
            )

            if (response.isSuccessful){

                emit(ResourceUI.Resource(response.body()?.toDto()))

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

            val listIds = favoriteDao.getIDsFromFavoriteList()


            val response = moviesApiService.getPopular(BuildConfig.API_KEY,page)

            if (response.isSuccessful){

                response.body()?.results?.forEach {

                    it.is_favorote = listIds.contains(it.id)

                }
                emit(ResourceUI.Resource(response.body()?.toDto()))

            }
            else
                emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {

            if (!context.isNetworkAvailable()) {

                emit(ResourceUI.Error(context.getString(R.string.no_access_network)))
            }
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

            if (!context.isNetworkAvailable()) {

                emit(ResourceUI.Error(context.getString(R.string.no_access_network)))
            }
            //emit(ResourceUI.Error(e.message))

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

            if (!context.isNetworkAvailable()) {

                emit(ResourceUI.Error(context.getString(R.string.no_access_network)))
            }
            e.printStackTrace()
        }

    }

    override suspend fun addFavorite(body: FavoriteRequestDto, itemDto: MoviePageItemDto): Flow<ResourceUI<SuccessDto>>  = flow{

        emit(ResourceUI.Loading)

        try {

            val successDto = SuccessDto(2000,"",true)

            itemDto.is_favorote = body.favorite

            if (body.favorite){

                val id = favoriteDao.addFavoriteMovie(itemDto.toEntity())
                if(id == -1L) successDto.success = false


            }else{

               val count  =  favoriteDao.deleteFavoriteMovie(body.mediaId.toLong())

                if(count == 0) successDto.success = false

            }

            emit(ResourceUI.Resource(successDto))


//            val response = moviesApiService.addFavoriteMovies(BuildConfig.ACCOUNT_ID.toInt(), BuildConfig.API_KEY, BuildConfig.SESSION_ID, body.toRequest())
//
//            if (response.isSuccessful){
//
//                itemDto.is_favorote = body.favorite
//
//                if (body.favorite){
//
//                    favoriteDao.addFavoriteMovie(itemDto.toEntity())
//
//                }else{
//
//                    favoriteDao.deleteFavoriteMovie(body.mediaId.toLong())
//                }
//
//
//                emit(ResourceUI.Resource(response.body()?.toDto()))
//
//            }
//
//            else emit(ResourceUI.Error(response.message()))


        } catch (e: Exception) {

            e.printStackTrace()
        }
    }


}