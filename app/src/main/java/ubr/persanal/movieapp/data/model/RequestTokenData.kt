package ubr.persanal.movieapp.data.model

data class RequestTokenData(
    val request_token: String,
    val status_code: Int,
    val status_message: String,
    val success: Boolean
)

data class PopularMovieResponse(
    val page: Int,
    val results: List<MovieItemData>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieItemData(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class TopRatedResponse(
    val page: Int,
    val results: List<MovieItemData>,
    val total_pages: Int,
    val total_results: Int
)

data class UpComingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<MovieItemData>,
    val total_pages: Int,
    val total_results: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)

