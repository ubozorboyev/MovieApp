package ubr.persanal.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class FavoriteRequest(
    @SerializedName("favorite")   val favorite: Boolean,
    @SerializedName("media_id")   val mediaId: Int,
    @SerializedName("media_type") val mediaType: String
)