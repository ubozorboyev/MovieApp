package ubr.persanal.onlineshop.data.api

import retrofit2.Response
import retrofit2.http.*
import ubr.persanal.movieapp.data.model.PopularMovieResponse
import ubr.persanal.movieapp.data.model.RequestTokenData
import ubr.persanal.movieapp.data.model.TopRatedResponse
import ubr.persanal.movieapp.data.model.UpComingResponse

interface ApiInterface {


    @POST("/auth/request_token")
    suspend fun getRequestToken(
        @Body body: String,
        @Header("Authorization") token: String,
    ): Response<RequestTokenData>


    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int,
    ): Response<PopularMovieResponse>


    @GET("3/movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key") api_key: String
    ): Response<TopRatedResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpComing(
        @Query("api_key") api_key: String
    ): Response<UpComingResponse>


}

