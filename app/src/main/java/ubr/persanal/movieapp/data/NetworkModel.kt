package ubr.persanal.movieapp.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ubr.persanal.movieapp.common.Common
import ubr.persanal.onlineshop.data.api.ApiInterface
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModel {


    @Provides
    fun getOkHttp(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun getApiService(okHttpClient: OkHttpClient): ApiInterface = Retrofit.Builder()
        .baseUrl(Common.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiInterface::class.java)



}