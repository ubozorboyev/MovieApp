package ubr.persanal.movieapp.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.domain.source.ActorDataSource
import ubr.persanal.movieapp.util.ResourceUI
import ubr.persanal.onlineshop.data.api.ActorsApiService
import java.lang.Exception
import javax.inject.Inject

class ActorDataSourceImpl @Inject constructor(
    private val apiService: ActorsApiService
) : ActorDataSource {


    override suspend fun getDetailActor(personId: Int): Flow<ResourceUI<ActorDetailDto>> = flow {

        emit(ResourceUI.Loading)

        try {

            val response = apiService.getActorDetails(personId,BuildConfig.API_KEY)
            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))
            else emit(ResourceUI.Error(response.message()))


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun getActors(movieId: Int): Flow<ResourceUI<MovieActorsDto>>  = flow {

        emit(ResourceUI.Loading)

        try {
            val response = apiService.getMovieActors(movieId, BuildConfig.API_KEY)

            if (response.isSuccessful)
                emit(ResourceUI.Resource(response.body()?.toDto()))

            else emit(ResourceUI.Error(response.message()))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResourceUI.Error(e.message))
        }
    }
}