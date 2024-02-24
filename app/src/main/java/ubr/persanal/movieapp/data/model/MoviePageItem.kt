package ubr.persanal.movieapp.data.model

import android.graphics.BitmapFactory
import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.BuildConfig
import ubr.persanal.movieapp.data.local.MoviePageItemEntity
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.util.BitmapConverter
import java.io.Serializable
import java.net.URL


data class MoviePageItem(
    
    @SerializedName("adult")
    val adult: Boolean? = null,
    
    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,
    
//    @SerializedName("genre_ids")
//    val genre_ids: List<Int>? = null,
//
    @SerializedName("id")
    val id: Long? = null,
    
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
    val vote_count: Int? = null,

    var is_favorote: Boolean? = false,

    var imageString: String? = null,


    ):Serializable{

    fun toDto():MoviePageItemDto{

        return MoviePageItemDto(
            adult,
            backdrop_path,
            id,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count,
            is_favorote,
            imageString
        )
    }

    fun toEntity():MoviePageItemEntity{


        return MoviePageItemEntity(
            adult,
            backdrop_path,
            id,
            original_language,
            original_title,
            overview,
            popularity,
            poster_path,
            release_date,
            title,
            video,
            vote_average,
            vote_count,
            is_favorote,
            imageString
        )
    }
}
