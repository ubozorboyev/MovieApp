package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import java.io.Serializable

data class MovieDetailsResponse(

    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = null,

   // @SerializedName("belongs_to_collection")
   // val belongs_to_collection: BelongsToCollection? = null,

    @SerializedName("budget")
    val budget: Int? = null,

    //@SerializedName("genres")
   // val genres: List<Genre>? = null,

    @SerializedName("homepage")
    val homepage: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("imdb_id")
    val imdb_id: String? = null,

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

//    @SerializedName("production_companies")
//    val production_companies: List<ProductionCompany>? = null,
//
//    @SerializedName("production_countries")
//    val production_countries: List<ProductionCountry>? = null,
//
    @SerializedName("release_date")
    val release_date: String? = null,

    @SerializedName("revenue")
    val revenue: Int? = null,

    @SerializedName("runtime")
    val runtime: Int? = null,

    //@SerializedName("spoken_languages")
   // val spoken_languages: List<SpokenLanguage>? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("tagline")
    val tagline: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val vote_average: Double? = null,

    @SerializedName("vote_count")
    val vote_count: Int? = null
):Serializable{



    fun toDto():MovieDetailsDto{

        return MovieDetailsDto(
            adult = adult,
            backdrop_path = backdrop_path,
            budget = budget,
            homepage = homepage,
            id = id,
            imdb_id = imdb_id,
            original_language = original_language,
            original_title = original_title,
            overview = overview,
            popularity = popularity,
            poster_path = poster_path,
            release_date = release_date,
            revenue = revenue,
            runtime = runtime,
            status = status,
            tagline = tagline,
            title = title,
            video = video,
            vote_average = vote_average,
            vote_count = vote_count
        )
    }

}

//data class BelongsToCollection(
//    val backdrop_path: String? = null,
//    val id: Int? = null,
//    val name: String? = null,
//    val poster_path: String?=null
//)
//
//data class Genre(
//    val id: Int? = null,
//    val name: String? = null
//)
//
//data class ProductionCompany(
//    val id: Int,
//    val logo_path: String,
//    val name: String,
//    val origin_country: String
//)
//
//data class ProductionCountry(
//    val iso_3166_1: String,
//    val name: String
//)
//
//data class SpokenLanguage(
//    val english_name: String,
//    val iso_639_1: String,
//    val name: String
//)