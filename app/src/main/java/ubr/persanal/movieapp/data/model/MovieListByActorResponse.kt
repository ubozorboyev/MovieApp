package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import java.io.Serializable

data class MovieListByActorResponse(
    @SerializedName("id")   val id: Int? = null,
    @SerializedName("cast") val cast: List<MovieByActorItem>? = null,
    @SerializedName("crew") val crew: List<CrewActorItem> ? =null
):Serializable{

    fun toDto():MovieListByActorDto{

        return MovieListByActorDto(
            id = id,
            cast = cast?.map { it.toDto() },
            crew = crew?.map { it.toDto() }
        )
    }

}


