package ubr.persanal.movieapp.domain.model

import java.io.Serializable

data class MovieListByActorDto(
    val id: Int? = null,
    val cast: List<MovieByActorItemDto>? = null,
    val crew: List<CrewActorItemDto> ? =null
):Serializable


