package ubr.persanal.movieapp.ui.screen.upcoming

import android.util.Log
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.domain.model.FavoriteRequestDto
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.model.SuccessDto
import ubr.persanal.movieapp.domain.usecase.GetUpComingFilmsUseCase
import ubr.persanal.movieapp.domain.usecase.SetMovieFavoriteUseCase
import ubr.persanal.movieapp.ui.source.UpComingMoviePageDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(
    private val useCase: GetUpComingFilmsUseCase,
    private val setMovieFavoriteUseCase: SetMovieFavoriteUseCase,
) :
    ViewModel() {



    private val _successFavorite = MutableLiveData<ResourceUI<SuccessDto>>()
    val successFavorite: LiveData<ResourceUI<SuccessDto>> = _successFavorite




    val upComingListPager: Flow<PagingData<MoviePageItemDto>> =

        Pager(PagingConfig(pageSize = 10, enablePlaceholders = true)) {
            UpComingMoviePageDataSource(useCase)

        }.flow.cachedIn(viewModelScope)





    fun setFavoriteMovie(requestDto: FavoriteRequestDto, itemDto: MoviePageItemDto){

        viewModelScope.launch {

            setMovieFavoriteUseCase.invoke(requestDto, itemDto).collectLatest {

                _successFavorite.value = it
            }
        }

    }

}