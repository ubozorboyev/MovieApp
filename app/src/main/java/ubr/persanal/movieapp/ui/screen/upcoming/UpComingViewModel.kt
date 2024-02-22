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
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.domain.model.MoviePageItemDto
import ubr.persanal.movieapp.domain.model.MoviePagingDto
import ubr.persanal.movieapp.domain.usecase.GetUpComingFilmsUseCase
import ubr.persanal.movieapp.ui.source.UpComingMoviePageDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(private val useCase: GetUpComingFilmsUseCase) :
    ViewModel() {


    private val _dataList: MutableLiveData<ResourceUI<MoviePagingDto>> =
        MutableLiveData()
    val dataList: LiveData<ResourceUI<MoviePagingDto>> = _dataList



    val upComingListPager: Flow<PagingData<MoviePageItemDto>> =

        Pager(PagingConfig(pageSize = 10, enablePlaceholders = false)) {
            UpComingMoviePageDataSource(useCase)

        }.flow.cachedIn(viewModelScope)

    init {

        Log.d("ViewModel", "UpComingViewModel: ")
        fetchData()
    }

     fun fetchData() {


//        viewModelScope.launch {
//            useCase.invoke(1).collect {
//                _dataList.postValue(it)
//            }
//
//        }
    }

}