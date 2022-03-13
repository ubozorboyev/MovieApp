package ubr.persanal.movieapp.data.repo

import kotlinx.coroutines.flow.flow
import ubr.persanal.movieapp.common.Common
import ubr.persanal.movieapp.util.DataState
import ubr.persanal.onlineshop.data.api.ApiInterface
import java.lang.Exception
import javax.inject.Inject

class AboutMovieRepository @Inject constructor(private val apiInterface: ApiInterface) {


    suspend fun getMovieDetails(movieId: Int) = flow {

        emit(DataState.Loading)

        try {
            val response = apiInterface.getMovieDetails(movieId, Common.api_key)
            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getActors(movieId: Int) = flow {

        emit(DataState.Loading)
        try {
            val response = apiInterface.getMovieActors(movieId, Common.api_key)
            if (response.isSuccessful)
                emit(DataState.ResponseData(response.body()))
            else emit(DataState.Error(response.message()))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}