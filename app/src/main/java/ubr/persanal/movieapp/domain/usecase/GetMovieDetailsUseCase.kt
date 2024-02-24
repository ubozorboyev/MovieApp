package ubr.persanal.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.util.ResourceUI

interface GetMovieDetailsUseCase {

    suspend fun invoke(movieId: Long):Flow<ResourceUI<MovieDetailsDto>>


}