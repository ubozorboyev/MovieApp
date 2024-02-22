package ubr.persanal.movieapp.data.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.usecase.GetMovieDetailsUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class GetMovieDetailsUseCaseImpl @Inject constructor( private val repository: MovieRepository):
    GetMovieDetailsUseCase {

    override suspend fun invoke(movieId:Int): Flow<ResourceUI<MovieDetailsDto>> {
        return repository.getMovieDetails(movieId)
    }
}