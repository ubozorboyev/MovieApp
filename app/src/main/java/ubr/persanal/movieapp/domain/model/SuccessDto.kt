package ubr.persanal.movieapp.domain.model

import java.io.Serializable

data class SuccessDto(
    var statusCode: Int,
    var statusMessage: String,
    var success: Boolean
):Serializable