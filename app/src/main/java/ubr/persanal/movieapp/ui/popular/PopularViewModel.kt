package ubr.persanal.movieapp.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.data.model.MovieItemData
import ubr.persanal.movieapp.data.model.PopularMovieResponse
import ubr.persanal.movieapp.data.repo.PopularRepository
import ubr.persanal.movieapp.util.DataState
import javax.inject.Inject


@HiltViewModel
class PopularViewModel @Inject constructor(private val repository: PopularRepository) :
    ViewModel() {

    private val _dataList: MutableLiveData<DataState<PopularMovieResponse>> =
        MutableLiveData()
    val dataList: LiveData<DataState<PopularMovieResponse>> = _dataList


    init {
        fetchData()
        Log.d("ViewModel", "PopularViewModel: ")
    }

     fun fetchData() {
        viewModelScope.launch {
            repository.getPoplarFilms().collect {
                _dataList.postValue(it)
            }

        }
    }


}