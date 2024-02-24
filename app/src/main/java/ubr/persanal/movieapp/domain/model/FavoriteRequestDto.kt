package ubr.persanal.movieapp.domain.model

import ubr.persanal.movieapp.data.model.FavoriteRequest

data class FavoriteRequestDto(
    val favorite: Boolean,
    val mediaId: Int,
    val mediaType: String,
){

    fun toRequest():FavoriteRequest{
        return FavoriteRequest(
            favorite, mediaId, mediaType
        )
    }
}