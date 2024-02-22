package ubr.persanal.movieapp.data.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.usecase.GetPoplarFilmsUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class GetPoplarFilmsUseCaseImpl @Inject constructor(private val repository: MovieRepository):
    GetPoplarFilmsUseCase {

    override suspend fun invoke(page: Int): Flow<ResourceUI<MoviePagingDto>> {

        return repository.getPoplarFilms(page)
    }

}