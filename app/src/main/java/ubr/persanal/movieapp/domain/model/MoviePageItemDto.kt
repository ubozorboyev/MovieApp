package ubr.persanal.movieapp.domain.model

import ubr.persanal.movieapp.data.local.MoviePageItemEntity
import java.io.Serializable

data class MoviePageItemDto(

    val adult: Boolean? = null,

    val backdrop_path: String? = null,

    //val genre_ids: List<Int>? = null,

    val id: Long? = null,

    val original_language: String? = null,

    val original_title: String? = null,

    val overview: String? = null,

    val popularity: Double? = null,

    val poster_path: String? = null,

    val release_date: String? = null,

    val title: String? = null,

    val video: Boolean? = null,

    val vote_average: Double? = null,

    val vote_count: Int? = null,

    var is_favorote: Boolean? = false,

    var imageString: String? = null,


    ):Serializable{

    fun toEntity(): MoviePageItemEntity {


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
