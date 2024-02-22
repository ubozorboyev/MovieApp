package ubr.persanal.movieapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.util.ResourceUI

interface GetDetailActorUseCase {

    suspend fun invoke(personId: Int): Flow<ResourceUI<ActorDetailDto>>

}