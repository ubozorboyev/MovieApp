package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class TokenRequest(

    @SerializedName("request_token")
    val request_token: String,

    @SerializedName("status_code")
    val status_code: Int,

    @SerializedName("status_message")
    val status_message: String,

    @SerializedName("success")
    val success: Boolean
)




