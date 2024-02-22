package ubr.persanal.onlineshop.data.api

import retrofit2.Response
import retrofit2.http.*
import ubr.persanal.movieapp.data.model.*

interface ActorsApiService {


    @GET("3/movie/{id}/credits")
    suspend fun getMovieActors(
        @Path("id") movieId: Int,
        @Query("api_key") api_key: String
    ): Response<MovieActorsResponse>


    @GET("3/person/{person_id}")
    suspend fun getActorDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") api_key: String
    ): Response<ActorDetailResponse>

}

