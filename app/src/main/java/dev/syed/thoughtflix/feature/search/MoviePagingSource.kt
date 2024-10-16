package dev.syed.thoughtflix.feature.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.syed.thoughtflix.data.remote.Movie
import dev.syed.thoughtflix.network.MovieApiService

class MoviePagingSource(
    private val apiService: MovieApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val page = params.key ?: 1
            val response = if (query.isEmpty()) {
                Log.d("syedlog","request trending")
                apiService.getTrendingMovies(page)
            } else {
                Log.d("syedlog","request query search")
                apiService.searchMovies(query, page)
            }
            val movies = response.body()?.results ?: emptyList()
            LoadResult.Page(
                data = movies,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (movies.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}