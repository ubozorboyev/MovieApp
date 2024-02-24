package ubr.persanal.movieapp.ui.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.domain.usecase.GetMoviesFavoriteUseCase
import ubr.persanal.movieapp.domain.usecase.SetMovieFavoriteUseCase
import ubr.persanal.movieapp.ui.source.FavoriteMoviePageDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesFavoriteUseCase,
    private val setMovieFavoriteUseCase: SetMovieFavoriteUseCase,

): ViewModel(){


    private val _successFavorite = MutableLiveData<ResourceUI<SuccessDto>>()
    val successFavorite: LiveData<ResourceUI<SuccessDto>> = _successFavorite

    val favoriteListPager: Flow<PagingData<MoviePageItemDto>> =

        Pager(PagingConfig(pageSize = 10, enablePlaceholders = true)) {
            FavoriteMoviePageDataSource(getMoviesUseCase)

        }.flow.cachedIn(viewModelScope)


    fun setFavoriteMovie(requestDto: FavoriteRequestDto, itemDto: MoviePageItemDto){

        viewModelScope.launch {

            setMovieFavoriteUseCase.invoke(requestDto, itemDto).collectLatest {

                _successFavorite.value = it
            }
        }

    }

}