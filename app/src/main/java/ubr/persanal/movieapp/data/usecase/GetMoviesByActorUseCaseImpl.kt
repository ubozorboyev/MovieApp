package ubr.persanal.movieapp.data.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.usecase.GetMoviesByActorUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class GetMoviesByActorUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
): GetMoviesByActorUseCase {
    override suspend fun invoke(personId: Int): Flow<ResourceUI<MovieListByActorDto>> {

        return repository.getMoviesByActor(personId)
    }


}