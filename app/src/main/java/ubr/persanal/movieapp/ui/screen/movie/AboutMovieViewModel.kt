package ubr.persanal.movieapp.ui.screen.movie

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ubr.persanal.movieapp.data.model.MovieActorsResponse
import ubr.persanal.movieapp.data.model.MovieDetailsResponse
import ubr.persanal.movieapp.data.repo.MovieRepositoryImpl
import ubr.persanal.movieapp.util.ResourceUI
import javax.inject.Inject

@HiltViewModel
class AboutMovieViewModel @Inject constructor(
    private val repository: MovieRepositoryImpl,
    private val savedState: SavedStateHandle
) : ViewModel() {


    private val _movieDetail: MutableLiveData<ResourceUI<MovieDetailsResponse>> =
        MutableLiveData()
    val movieDetail: LiveData<ResourceUI<MovieDetailsResponse>> = _movieDetail

    private val _movieActors: MutableLiveData<ResourceUI<MovieActorsResponse>> =
        MutableLiveData()
    val movieActors: LiveData<ResourceUI<MovieActorsResponse>> = _movieActors


    init {
        fetchMovieDetails()
    }

    private fun fetchMovieDetails() {

        val movieId = savedState.get<Int>("MOVIE_ID") ?: -1

        viewModelScope.launch {

            repository.getMovieDetails(movieId).collect {
                //_movieDetail.postValue(it)
            }

//            repository.getActors(movieId).collect {
//                _movieActors.postValue(it)
//            }
        }

    }


}