package ubr.persanal.movieapp.domain.source

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.util.ResourceUI

interface ActorDataSource {


    suspend fun getDetailActor(personId: Int): Flow<ResourceUI<ActorDetailDto>>

    suspend fun getActors(movieId: Long) : Flow<ResourceUI<MovieActorsDto>>


}