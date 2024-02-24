package ubr.persanal.movieapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [MoviePageItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {


    abstract fun getFavoriteDao():FavoriteDao

    companion object{

        @Volatile
        private var instance:AppDatabase? = null


        operator fun invoke(context: Context) = instance?: synchronized(this){

            instance = buildDataBase(context)

            instance!!
        }


        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "movie.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    }



}