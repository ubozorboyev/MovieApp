package ubr.persanal.movieapp.ui.screen.popular

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
import ubr.persanal.movieapp.domain.usecase.GetPoplarFilmsUseCase
import ubr.persanal.movieapp.ui.source.PopularMoviePageDataSource
import ubr.persanal.movieapp.ui.source.TopRatedMoviePageDataSource
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(private val useCase: GetPoplarFilmsUseCase) :
    ViewModel() {




    val popularListPager: Flow<PagingData<MoviePageItemDto>> =

        Pager(PagingConfig(pageSize = 10, enablePlaceholders = false)) {
            PopularMoviePageDataSource(useCase)

        }.flow.cachedIn(viewModelScope)
    init {
        fetchData()
        Log.d("ViewModel", "PopularViewModel: ")
    }

     fun fetchData() {

    }


}