package dev.syed.thoughtflix.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import dev.syed.thoughtflix.data.remote.Movie
import dev.syed.thoughtflix.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchViewmodel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movies: StateFlow<PagingData<Movie>> = _movies

    init {
        updateQuery("")
    }
    fun updateQuery(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            movieRepository.getSearchMovies(newQuery).cachedIn(viewModelScope).collectLatest {
                _movies.value = it.filter { movie -> movie.backdropPath != null  && movie.posterPath != null }
            }
        }
    }
}