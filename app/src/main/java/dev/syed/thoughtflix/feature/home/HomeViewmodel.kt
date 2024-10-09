package dev.syed.thoughtflix.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.syed.thoughtflix.data.remote.MovieResponse
import dev.syed.thoughtflix.repository.MovieRepository
import dev.syed.thoughtflix.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewmodel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _popularMovies = MutableStateFlow<Resource<MovieResponse>?>(null)
    val popularMovies: StateFlow<Resource<MovieResponse>?> = _popularMovies


    private val _topRatedMovies = MutableStateFlow<Resource<MovieResponse>?>(null)
    val topRatedMovies: StateFlow<Resource<MovieResponse>?> = _topRatedMovies


    private val _nowPlayingMovies = MutableStateFlow<Resource<MovieResponse>?>(null)
    val nowPlayingMovies: StateFlow<Resource<MovieResponse>?> = _nowPlayingMovies


    private val _upcomingMovies = MutableStateFlow<Resource<MovieResponse>?>(null)
    val upcomingMovies: StateFlow<Resource<MovieResponse>?> = _upcomingMovies

    fun getPopularMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getPopularMovies(pageNo).let {
                _popularMovies.value = it
            }
        }
    }

    fun getTopRatedMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getTopRatedMovies(pageNo).let {
                _topRatedMovies.value = it
            }
        }
    }

    fun getUpcomingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies(pageNo).let {
                _upcomingMovies.value = it
            }
        }
    }

    fun getNowPlayingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getNowPlayingMovies(pageNo).let {
                _nowPlayingMovies.value = it
            }
        }
    }
}