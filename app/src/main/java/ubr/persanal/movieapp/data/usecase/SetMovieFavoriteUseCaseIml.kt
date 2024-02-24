package ubr.persanal.movieapp.data.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.usecase.SetMovieFavoriteUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class SetMovieFavoriteUseCaseIml @Inject constructor(
    private val repository: MovieRepository
): SetMovieFavoriteUseCase {

    override suspend fun invoke(
        body: FavoriteRequestDto,
        movieItemDto: MoviePageItemDto
    ): Flow<ResourceUI<SuccessDto>> {

        return repository.addFavorite(body,movieItemDto)

    }
}