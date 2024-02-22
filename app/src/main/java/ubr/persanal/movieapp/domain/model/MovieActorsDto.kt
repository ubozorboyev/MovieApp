package ubr.persanal.movieapp.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieActorsDto(
    val cast: List<ActorItemDto>?,
    val crew: List<CrewItemDto>?,
    val id: Int?
):Serializable



