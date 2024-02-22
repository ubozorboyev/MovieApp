package ubr.persanal.movieapp.domain.model

data class MoviePagingDto(

    val page: Int,

    val results: List<MoviePageItemDto> = emptyList(),

    val total_pages: Int,

    val total_results: Int
)