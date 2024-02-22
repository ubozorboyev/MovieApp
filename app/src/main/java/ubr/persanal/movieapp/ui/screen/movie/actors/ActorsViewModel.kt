package ubr.persanal.movieapp.ui.screen.movie.actors

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.domain.model.ActorDetailDto
import ubr.persanal.movieapp.domain.model.MovieListByActorDto
import ubr.persanal.movieapp.domain.usecase.GetDetailActorUseCase
import ubr.persanal.movieapp.domain.usecase.GetMoviesByActorUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getMoviesByActorUseCase: GetMoviesByActorUseCase,
    private val getDetailActorUseCase: GetDetailActorUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _movieList: MutableLiveData<ResourceUI<MovieListByActorDto>> =
        MutableLiveData()
    val movieList: LiveData<ResourceUI<MovieListByActorDto>> = _movieList

    private val _detailActor: MutableLiveData<ResourceUI<ActorDetailDto>> =
        MutableLiveData()
    val detailActor: LiveData<ResourceUI<ActorDetailDto>> = _detailActor


    init {

        val personId = savedStateHandle.get<Int>("PERSON_ID") ?: -1

        getMoviesByActor(personId)

        getDetailActor(personId)


    }

    private fun getMoviesByActor(personId:Int) {


        viewModelScope.launch {

            getMoviesByActorUseCase.invoke(personId).collect {
                _movieList.postValue(it)
            }


        }
    }

    private fun getDetailActor(personId: Int){

        viewModelScope.launch {

            getDetailActorUseCase.invoke(personId).collect {
                _detailActor.postValue(it)
            }
        }

    }




}