package ubr.persanal.movieapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.domain.reposotory.ActorRepository
import ubr.persanal.movieapp.domain.source.ActorDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class ActorRepositoryImpl @Inject constructor(private val actorDataSource: ActorDataSource):ActorRepository {

    override suspend fun getActors(movieId: Int): Flow<ResourceUI<MovieActorsDto>>  = withContext(Dispatchers.IO){

        actorDataSource.getActors(movieId)

    }


    override suspend fun getDetailActor(personId: Int) = withContext(Dispatchers.IO){

        actorDataSource.getDetailActor(personId)
    }

}