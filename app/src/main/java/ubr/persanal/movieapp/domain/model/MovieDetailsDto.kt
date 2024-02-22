package ubr.persanal.movieapp.domain.model

import java.io.Serializable

data class MovieDetailsDto(

    val adult: Boolean? = null,

    val backdrop_path: String? = null,

   //
   // val belongs_to_collection: BelongsToCollection? = null,

    val budget: Int? = null,

   // val genres: List<Genre>? = null,

    val homepage: String? = null,

    val id: Int? = null,

    val imdb_id: String? = null,

    val original_language: String? = null,

    val original_title: String? = null,

    val overview: String? = null,

    val popularity: Double? = null,

    val poster_path: String? = null,

//
//    val production_companies: List<ProductionCompany>? = null,
//
//
//    val production_countries: List<ProductionCountry>? = null,
//
    val release_date: String? = null,

    val revenue: Int? = null,

    val runtime: Int? = null,


   // val spoken_languages: List<SpokenLanguage>? = null,

    val status: String? = null,

    val tagline: String? = null,

    val title: String? = null,

    val video: Boolean? = null,

    val vote_average: Double? = null,

    val vote_count: Int? = null
):Serializable

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