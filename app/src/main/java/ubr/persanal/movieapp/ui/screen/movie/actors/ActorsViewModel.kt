package ubr.persanal.movieapp.ui.screen.movie.actors

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.data.model.MovieListByActorResponse
import ubr.persanal.movieapp.data.model.ActorDetailResponse
import ubr.persanal.movieapp.data.repo.ActorRepositoryImpl
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val repository: ActorRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _dataList: MutableLiveData<ResourceUI<MovieListByActorResponse>> =
        MutableLiveData()
    val dataList: LiveData<ResourceUI<MovieListByActorResponse>> = _dataList

    private val _detailPerson: MutableLiveData<ResourceUI<ActorDetailResponse>> =
        MutableLiveData()
    val detailPerson: LiveData<ResourceUI<ActorDetailResponse>> = _detailPerson


    init {
        getMoviesByActor()
    }

    private fun getMoviesByActor() {

        val personId = savedStateHandle.get<Int>("PERSON_ID") ?: -1

        viewModelScope.launch {
//            repository.getMoviesByActor(personId).collect {
//                _dataList.postValue(it)
//            }

            repository.getDetailActor(personId).collect {
                //_detailPerson.postValue(it)
            }
        }
    }


}