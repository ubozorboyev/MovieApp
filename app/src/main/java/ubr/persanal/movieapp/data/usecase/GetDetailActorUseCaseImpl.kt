package ubr.persanal.movieapp.data.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.domain.reposotory.ActorRepository
import ubr.persanal.movieapp.domain.usecase.GetDetailActorUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

class GetDetailActorUseCaseImpl @Inject constructor( private val repository: ActorRepository):
    GetDetailActorUseCase {

    override suspend fun invoke(personId: Int): Flow<ResourceUI<ActorDetailDto>> {
        return repository.getDetailActor(personId)
    }

}