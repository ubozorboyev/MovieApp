package ubr.persanal.movieapp.domain.model

import java.io.Serializable

data class SuccessDto(
    val statusCode: Int,
    val statusMessage: String,
    val success: Boolean
):Serializable