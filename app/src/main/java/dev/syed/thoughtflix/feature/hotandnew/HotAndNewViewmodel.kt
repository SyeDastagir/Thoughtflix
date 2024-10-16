package dev.syed.thoughtflix.feature.hotandnew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dev.syed.thoughtflix.data.remote.Movie
import dev.syed.thoughtflix.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HotAndNewViewmodel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _trendingMovies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val trendingMovies: StateFlow<PagingData<Movie>> = _trendingMovies

    fun getTrendingMovies(pageNo: Int) {
        viewModelScope.launch {
            movieRepository.getSearchMovies("").cachedIn(viewModelScope).collectLatest {
                _trendingMovies.value = it
            }
        }
    }

}