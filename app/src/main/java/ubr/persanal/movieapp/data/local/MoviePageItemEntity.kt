package ubr.persanal.movieapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.data.model.MoviePageItem
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import java.io.Serializable

@Entity(tableName = "movie_table")
data class MoviePageItemEntity(
    
    @ColumnInfo("adult")
    var adult: Boolean? = null,
    
    @ColumnInfo("backdrop_path")
    var backdrop_path: String? = null,

    @PrimaryKey(autoGenerate = false)
    var id: Long? = null,
    
    @ColumnInfo("original_language")
    var original_language: String? = null,
    
    @ColumnInfo("original_title")
    var original_title: String? = null,
    
    @ColumnInfo("overview")
    var overview: String? = null,
    
    @ColumnInfo("popularity")
    var popularity: Double? = null,
    
    @ColumnInfo("poster_path")
    var poster_path: String? = null,
    
    @ColumnInfo("release_date")
    var release_date: String? = null,
    
    @ColumnInfo("title")
    var title: String? = null,
    
    @ColumnInfo("video")
    var video: Boolean? = null,
    
    @ColumnInfo("vote_average")
    var vote_average: Double? = null,
    
    @ColumnInfo("vote_count")
    var vote_count: Int? = null,

    @ColumnInfo("is_favorote")
    var is_favorote: Boolean? = null,

    @ColumnInfo("image_string")
    var imageString: String? = null,


):Serializable {


    fun toDto(): MoviePageItemDto {

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
}
