package ubr.persanal.onlineshop.data.api

import retrofit2.Response
import retrofit2.http.*
import ubr.persanal.movieapp.data.model.*

interface MoviesApiService {


    @POST("/auth/request_token")
    suspend fun getRequestToken(
        @Body body: String,
        @Header("Authorization") token: String,
    ): Response<TokenRequest>


    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<MoviePagingResponse>


    @GET("3/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") api_key: String
    ): Response<MoviePagingResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpComing(
        @Query("api_key") api_key: String
    ): Response<MoviePagingResponse>


    @GET("3/movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") movieId: Int,
        @Query("api_key") api_key: String
    ): Response<MovieDetailsResponse>

    @GET("3/movie/{id}/credits")
    suspend fun getMovieActors(
        @Path("id") movieId: Int,
        @Query("api_key") api_key: String
    ): Response<MovieActorsResponse>

    @GET("3/person/{person_id}/movie_credits")
    suspend fun getMoviesByActor(
        @Path("person_id") personId: Int,
        @Query("api_key") api_key: String
    ): Response<MovieListByActorResponse>

    @GET("3/person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") api_key: String
    ): Response<ActorDetailResponse>

}

