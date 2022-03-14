package ubr.persanal.movieapp.ui.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.data.model.PopularMovieResponse
import ubr.persanal.movieapp.data.model.TopRatedResponse
import ubr.persanal.movieapp.data.repo.TopRatedRepository
import ubr.persanal.movieapp.util.DataState
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor(private val repository: TopRatedRepository) :
    ViewModel() {
    private val _dataList: MutableLiveData<DataState<TopRatedResponse>> =
        MutableLiveData()
    val dataList: LiveData<DataState<TopRatedResponse>> = _dataList


    init {
        fetchData()
        Log.d("ViewModel", "TopRatedViewModel: ")

    }

     fun fetchData() {
        viewModelScope.launch {
            repository.getTopRatedFilms().collect {
                _dataList.postValue(it)
            }

        }
    }

}