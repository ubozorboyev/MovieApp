package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import java.io.Serializable

data class ActorDetailResponse(

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("also_known_as")
    val also_known_as: List<String>? = null,

    @SerializedName("biography")
    val biography: String? = null,

    @SerializedName("birthday")
    val birthday: String? = null,

    @SerializedName("deathday")
    val deathday: Any? = null,

    @SerializedName("gender")
    val gender: Int? = null,

    @SerializedName("homepage")
    val homepage: Any? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("imdb_id")
    val imdb_id: String? = null,

    @SerializedName("known_for_department")
    val known_for_department: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("place_of_birth")
    val place_of_birth: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("profile_path")
    val profile_path: String? = null

):Serializable{

    fun toDto():ActorDetailDto{

        return ActorDetailDto(
            adult,
            also_known_as,
            biography,
            birthday,
            deathday,
            gender,
            homepage,
            id,
            imdb_id,
            known_for_department,
            name,
            place_of_birth,
            popularity,
            profile_path
        )
    }
}