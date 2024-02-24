package ubr.persanal.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.util.ResourceUI

interface SetMovieFavoriteUseCase {

    suspend fun invoke(body: FavoriteRequestDto, movieItemDto: MoviePageItemDto):Flow<ResourceUI<SuccessDto>>

}