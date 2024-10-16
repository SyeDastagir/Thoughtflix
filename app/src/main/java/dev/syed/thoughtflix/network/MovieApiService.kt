package dev.syed.thoughtflix.network

import dev.syed.thoughtflix.data.remote.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Query("movie_id") movieId: Int): Response<MovieResponse>
}