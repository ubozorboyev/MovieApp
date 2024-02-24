package ubr.persanal.movieapp.data.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ubr.persanal.movieapp.data.local.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {


    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase{
        return AppDatabase(app)
    }

    @Provides
    @Singleton
    fun provideFavoriteDao(appDatabase: AppDatabase) = appDatabase.getFavoriteDao()

}