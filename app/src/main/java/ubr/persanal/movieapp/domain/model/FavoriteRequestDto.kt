package ubr.persanal.movieapp.domain.model

data class FavoriteRequestDto(
    val favorite: Boolean,
    val mediaId: Int,
    val mediaType: String
)