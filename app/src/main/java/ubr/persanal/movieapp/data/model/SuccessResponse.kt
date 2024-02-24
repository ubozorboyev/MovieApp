package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName
import ubr.persanal.movieapp.domain.model.SuccessDto
import java.io.Serializable

data class SuccessResponse(
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("status_message") val statusMessage: String,
    @SerializedName("success") val success: Boolean
):Serializable{

    fun toDto():SuccessDto{

        return SuccessDto(statusCode, statusMessage, success)
    }

}