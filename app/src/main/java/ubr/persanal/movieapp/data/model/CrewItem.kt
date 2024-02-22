package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.CrewItemDto
import java.io.Serializable

data class CrewItem(

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("credit_id")
    val credit_id: String? = null,

    @SerializedName("department")
    val department: String ? =null,

    @SerializedName("gender")
    val gender: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("job")
    val job: String? = null,

    @SerializedName("known_for_department")
    val known_for_department: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("original_name")
    val original_name: String? = null,

    @SerializedName("popularity")
    val popularity: Double? = null,

    @SerializedName("profile_path")
    val profile_path: String? = null
):Serializable{

    fun toDto():CrewItemDto{

        return CrewItemDto(
            adult,
            credit_id,
            department,
            gender,
            id,
            job,
            known_for_department,
            name,
            original_name,
            popularity,
            profile_path
        )
    }

}