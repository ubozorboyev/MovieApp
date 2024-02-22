package ubr.persanal.movieapp.ui.screen.toprated

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
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.usecase.GetTopRatedFilmsUseCase
import ubr.persanal.movieapp.ui.source.TopRatedMoviePageDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val topRatedUseCase: GetTopRatedFilmsUseCase) :
    ViewModel() {

    private val _dataList: MutableLiveData<ResourceUI<MoviePagingDto>> =
        MutableLiveData()

    val dataList: LiveData<ResourceUI<MoviePagingDto>> = _dataList


    init {
        fetchData()
        Log.d("ViewModel", "TopRatedViewModel: ")

    }

    val topRatedListPager: Flow<PagingData<MoviePageItemDto>> =

        Pager(PagingConfig(pageSize = 10, enablePlaceholders = false)) {
            TopRatedMoviePageDataSource(topRatedUseCase)

        }.flow.cachedIn(viewModelScope)

     fun fetchData() {
        viewModelScope.launch {
            topRatedUseCase.invoke(1).collect {
                _dataList.postValue(it)
            }

        }
    }

}