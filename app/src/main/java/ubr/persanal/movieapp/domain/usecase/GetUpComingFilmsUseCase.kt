package ubr.persanal.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.util.ResourceUI

interface GetUpComingFilmsUseCase {

    suspend fun invoke(page:Int): Flow<ResourceUI<MoviePagingDto>>


}