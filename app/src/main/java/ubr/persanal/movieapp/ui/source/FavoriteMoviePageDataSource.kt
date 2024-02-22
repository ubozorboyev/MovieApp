package ubr.persanal.movieapp.ui.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ubr.persanal.movieapp.common.Common.START_PAGE
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.usecase.GetMoviesFavoriteUseCase
import ubr.persanal.movieapp.domain.usecase.GetUpComingFilmsUseCase
import ubr.persanal.movieapp.util.ResourceUI
import java.lang.NullPointerException

class FavoriteMoviePageDataSource constructor(
    private val useCase: GetMoviesFavoriteUseCase
) : PagingSource<Int, MoviePageItemDto>() {


    override fun getRefreshKey(state: PagingState<Int, MoviePageItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviePageItemDto> {

        val currentPageKey = params.key ?: START_PAGE

        val prevKey = if (currentPageKey == 0) null else currentPageKey - 1

        var result: ResourceUI<MoviePagingDto>? = null

        useCase.invoke(currentPageKey).collect {

            result = it


        }

        return when(result){

            is ResourceUI.Error ->{

                val throwable = (result as ResourceUI.Error).error

                LoadResult.Error(Throwable(throwable))
            }

            is ResourceUI.Resource -> {

                val data = (result as ResourceUI.Resource<MoviePagingDto>).data

                val totalPages = data?.total_pages

                val template = currentPageKey.plus(1)

                val nextKey = if (template < (totalPages ?: 0)) template else null

                if (data!= null)
                    LoadResult.Page(data.results, prevKey, nextKey)

                else LoadResult.Error(NullPointerException())

            }

            else -> {
                LoadResult.Error(NullPointerException())
            }
        }

    }


}