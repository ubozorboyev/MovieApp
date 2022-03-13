package ubr.persanal.movieapp.ui.movie.actors

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.data.model.MoviesByActorResponse
import ubr.persanal.movieapp.data.model.PersonDetailResponse
import ubr.persanal.movieapp.data.repo.ActorRepository
import ubr.persanal.movieapp.util.DataState
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val repository: ActorRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _dataList: MutableLiveData<DataState<MoviesByActorResponse>> =
        MutableLiveData()
    val dataList: LiveData<DataState<MoviesByActorResponse>> = _dataList

    private val _detailPerson: MutableLiveData<DataState<PersonDetailResponse>> =
        MutableLiveData()
    val detailPerson: LiveData<DataState<PersonDetailResponse>> = _detailPerson


    init {
        getMoviesByActor()
    }

    private fun getMoviesByActor() {

        val personId = savedStateHandle.get<Int>("PERSON_ID") ?: -1

        viewModelScope.launch {
            repository.getMoviesByActor(personId).collect {
                _dataList.postValue(it)
            }

            repository.getDetailActor(personId).collect {
                _detailPerson.postValue(it)
            }
        }
    }


}