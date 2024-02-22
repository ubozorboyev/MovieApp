package ubr.persanal.movieapp.data.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.domain.reposotory.ActorRepository
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.usecase.GetActorsUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class GetActorsUseCaseImpl @Inject constructor(private val repository: ActorRepository) : GetActorsUseCase {

    override suspend fun invoke(movieId: Int): Flow<ResourceUI<MovieActorsDto>> {
        return repository.getActors(movieId)
    }


}