package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import java.io.Serializable

data class MoviePagingResponse(

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MoviePageItem> = emptyList(),

    @SerializedName("total_pages")
    val total_pages: Int,

    @SerializedName("total_results")
    val total_results: Int

):Serializable {

    fun toDto():MoviePagingDto {

        return MoviePagingDto(

            page = page,

            results = results.map { it.toDto() },

            total_pages = total_pages,

            total_results = total_results


        )
    }

}