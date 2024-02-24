package ubr.persanal.movieapp.domain.reposotory

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.util.ResourceUI

interface ActorRepository {

    suspend fun getActors(movieId: Long) : Flow<ResourceUI<MovieActorsDto>>

    suspend fun getDetailActor(personId: Int):Flow<ResourceUI<ActorDetailDto>>



}