package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.CrewActorItemDto

data class CrewActorItem(
    
    @SerializedName("adult")
    val adult: Boolean? = null,
    
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    
    @SerializedName("credit_id")
    val credit_id: String? = null,
    
    @SerializedName("department")
    val department: String? = null,
    
    @SerializedName("genre_ids")
    val genre_ids: List<Int>? = null,
    
    @SerializedName("id")
    val id: Int? = null,
    
    @SerializedName("job")
    val job: String? = null,
    
    @SerializedName("original_language")
    val original_language: String? = null,
    
    @SerializedName("original_title")
    val original_title: String? = null,
    
    @SerializedName("overview")
    val overview: String? = null,
    
    @SerializedName("popularity")
    val popularity: Double? = null,
    
    @SerializedName("poster_path")
    val poster_path: String? = null,
    
    @SerializedName("release_date")
    val release_date: String? = null,
    
    @SerializedName("title")
    val title: String? = null,
    
    @SerializedName("video")
    val video: Boolean? = null,
    
    @SerializedName("vote_average")
    val vote_average: Double? = null,
    
    @SerializedName("vote_count")
    val vote_count: Int? =null
){

    fun toDto():CrewActorItemDto{

        return CrewActorItemDto(
            adult,
            backdrop_path,
            credit_id,
            department,
            genre_ids,
            id,
            job,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count
        )
    }
}