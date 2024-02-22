package ubr.persanal.movieapp.ui.screen.favorite

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.usecase.GetMoviesFavoriteUseCase
import ubr.persanal.movieapp.ui.source.FavoriteMoviePageDataSource
import ubr.persanal.movieapp.ui.source.UpComingMoviePageDataSource
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCase: GetMoviesFavoriteUseCase
): ViewModel(){


    val upComingListPager: Flow<PagingData<MoviePageItemDto>> =

        Pager(PagingConfig(pageSize = 10, enablePlaceholders = true)) {
            FavoriteMoviePageDataSource(useCase)

        }.flow.cachedIn(viewModelScope)
}