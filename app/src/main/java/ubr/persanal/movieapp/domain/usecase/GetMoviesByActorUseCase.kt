package ubr.persanal.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.util.ResourceUI

interface GetMoviesByActorUseCase {

    suspend fun invoke(personId: Int): Flow<ResourceUI<MovieListByActorDto>>

}