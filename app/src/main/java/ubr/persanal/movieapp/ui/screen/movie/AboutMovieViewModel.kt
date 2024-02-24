package ubr.persanal.movieapp.ui.screen.movie

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.domain.model.MovieActorsDto
import ubr.persanal.movieapp.domain.model.MovieDetailsDto
import ubr.persanal.movieapp.domain.usecase.GetActorsUseCase
import ubr.persanal.movieapp.domain.usecase.GetMovieDetailsUseCase
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class AboutMovieViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getActorsUseCase: GetActorsUseCase,
    private val savedState: SavedStateHandle
) : ViewModel() {


    private val _movieDetail: MutableLiveData<ResourceUI<MovieDetailsDto>> =
        MutableLiveData()
    val movieDetail: LiveData<ResourceUI<MovieDetailsDto>> = _movieDetail

    private val _movieActors: MutableLiveData<ResourceUI<MovieActorsDto>> =
        MutableLiveData()
    val movieActors: LiveData<ResourceUI<MovieActorsDto>> = _movieActors


    init {

        val movieId = savedState.get<Long>("MOVIE_ID") ?: -1

        fetchMovieDetails(movieId)

        getActorsByMovie(movieId)
    }

    private fun fetchMovieDetails(movieId: Long) {


        viewModelScope.launch {

            getMovieDetailsUseCase.invoke(movieId).collectLatest {
                _movieDetail.postValue(it)
            }

        }

    }


    private fun getActorsByMovie(movieId:Long){

        viewModelScope.launch {

            getActorsUseCase.invoke(movieId).collectLatest {
                _movieActors.postValue(it)

            }
        }

    }


}