package ubr.persanal.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM movie_table ORDER BY id LIMIT :limit OFFSET :offset")
    suspend fun getFavoriteMovies(limit:Int, offset:Int):List<MoviePageItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMoviesList(item: List<MoviePageItemEntity>):List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie( item: MoviePageItemEntity):Long

    @Query("SELECT id FROM movie_table")
    fun  getIDsFromFavoriteList():List<Long>

    @Query("DELETE FROM movie_table WHERE id== :movieId")
    fun deleteFavoriteMovie(movieId: Long)
    @Query("DELETE FROM movie_table")
    fun deleteTable()

}