package dev.syed.thoughtflix.feature.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.syed.thoughtflix.data.remote.MovieResponse
import dev.syed.thoughtflix.repository.MovieRepository
import dev.syed.thoughtflix.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _movie : MutableStateFlow<Resource<MovieResponse>> = MutableStateFlow(Resource.Loading())
    val movie: StateFlow<Resource<MovieResponse>> = _movie

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            _movie.value = movieRepository.getMovieDetail(movieId)
        }
    }

}