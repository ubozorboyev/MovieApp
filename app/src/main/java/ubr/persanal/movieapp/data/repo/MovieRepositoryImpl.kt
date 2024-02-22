package ubr.persanal.movieapp.data.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ubr.persanal.movieapp.domain.reposotory.MovieRepository
import ubr.persanal.movieapp.domain.source.MovieDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieDataSource: MovieDataSource):
    MovieRepository {


    override suspend fun getMovieDetails(movieId: Int) = withContext(Dispatchers.IO){

        movieDataSource.getMovieDetails(movieId)
    }

    override suspend fun getPoplarFilms(page: Int) = withContext(Dispatchers.IO){

        movieDataSource.getPoplarFilms(page)
    }

    override suspend fun getTopRatedFilms(page:Int) = withContext(Dispatchers.IO){

        movieDataSource.getTopRatedFilms(page)
    }

    override suspend fun getUpComingFilms(page:Int) = withContext(Dispatchers.IO){

        movieDataSource.getUpComingFilms(page)
    }


}