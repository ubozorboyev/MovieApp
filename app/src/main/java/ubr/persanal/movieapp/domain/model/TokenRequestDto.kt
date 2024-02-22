package ubr.persanal.movieapp.domain.model


data class TokenRequestDto(

    val request_token: String,

    val status_code: Int,

    val status_message: String,

    val success: Boolean
)




