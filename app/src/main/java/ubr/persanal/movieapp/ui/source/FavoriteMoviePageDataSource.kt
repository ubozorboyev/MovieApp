package ubr.persanal.movieapp.ui.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import ubr.persanal.movieapp.data.local.FavoriteDao
import ubr.persanal.movieapp.domain.model.MoviePageItemDto

class FavoriteMoviePageDataSource constructor(
    private val favoriteDao: FavoriteDao
) : PagingSource<Int, MoviePageItemDto>() {


    override fun getRefreshKey(state: PagingState<Int, MoviePageItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviePageItemDto> {


        return try {

            val currentPageKey = params.key ?: 0

            val entities = favoriteDao.getFavoriteMovies(params.loadSize,currentPageKey*params.loadSize)

            delay(500) // for showing page loading


            return LoadResult.Page(
                data  = entities.map { it.toDto() },
                prevKey = if (currentPageKey == 0) null else currentPageKey -1,
                nextKey = if(entities.isEmpty()) null else currentPageKey + 1

            )
        }catch (e:Exception){

            LoadResult.Error(Throwable(e.message))
        }




    }


}