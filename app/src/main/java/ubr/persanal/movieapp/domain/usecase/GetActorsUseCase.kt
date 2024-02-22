package ubr.persanal.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.util.ResourceUI

interface GetActorsUseCase {
    suspend fun invoke(movieId: Int) : Flow<ResourceUI<MovieActorsDto>>

}