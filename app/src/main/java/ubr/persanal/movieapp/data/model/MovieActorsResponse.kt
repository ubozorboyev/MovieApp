package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import java.io.Serializable

data class MovieActorsResponse(
    @SerializedName("cast") val cast: List<ActorItem>?,
    @SerializedName("crew") val crew: List<CrewItem>?,
    @SerializedName("id") val id: Int?
):Serializable{

    fun toDto():MovieActorsDto{

        return MovieActorsDto(
            cast = cast?.map { it.toDto() },
            crew = crew?.map { it.toDto() },
            id = id
        )
    }


}



