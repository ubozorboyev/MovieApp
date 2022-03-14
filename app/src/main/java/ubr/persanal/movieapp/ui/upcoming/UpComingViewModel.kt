package ubr.persanal.movieapp.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.data.model.PopularMovieResponse
import ubr.persanal.movieapp.data.model.UpComingResponse
import ubr.persanal.movieapp.data.repo.UpComingRepository
import ubr.persanal.movieapp.util.DataState
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(private val repository: UpComingRepository) :
    ViewModel() {


    private val _dataList: MutableLiveData<DataState<UpComingResponse>> =
        MutableLiveData()
    val dataList: LiveData<DataState<UpComingResponse>> = _dataList


    init {
        Log.d("ViewModel", "UpComingViewModel: ")
        fetchData()
    }

     fun fetchData() {
        viewModelScope.launch {
            repository.getUpComingFilms().collect {
                _dataList.postValue(it)
            }

        }
    }

}