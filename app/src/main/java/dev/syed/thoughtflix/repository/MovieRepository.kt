package dev.syed.thoughtflix.repository

import android.util.Log
import dev.syed.thoughtflix.data.remote.MovieResponse
import dev.syed.thoughtflix.network.MovieApiService
import dev.syed.thoughtflix.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MovieRepository(private val apiService: MovieApiService) {
    suspend fun getPopularMovies(pageNo: Int): Resource<MovieResponse> {
        return try {
            val response: Response<MovieResponse> = apiService.getPopularMovies(pageNo)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("syedlog","Success: ${result}")
                     Resource.Success(result)
                } ?: Resource.Error("No data found")
            } else {
                Log.d("syedlog","Error: ${response.message()}")
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.d("syedlog","Exception: ${e.message}")
            Resource.Error("Exception: ${e.message}")
        }
    }

    suspend fun getNowPlayingMovies(pageNo: Int): Resource<MovieResponse> {
        return try {
            val response: Response<MovieResponse> = apiService.getNowPlayingMovies(pageNo)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("syedlog","Success: ${result}")
                    Resource.Success(result)
                } ?: Resource.Error("No data found")
            } else {
                Log.d("syedlog","Error: ${response.message()}")
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.d("syedlog","Exception: ${e.message}")
            Resource.Error("Exception: ${e.message}")
        }
    }

    suspend fun getUpcomingMovies(pageNo: Int): Resource<MovieResponse> {
        return try {
            val response: Response<MovieResponse> = apiService.getUpcomingMovies(pageNo)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("syedlog","Success: ${result}")
                    Resource.Success(result)
                } ?: Resource.Error("No data found")
            } else {
                Log.d("syedlog","Error: ${response.message()}")
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.d("syedlog","Exception: ${e.message}")
            Resource.Error("Exception: ${e.message}")
        }
    }

    suspend fun getTopRatedMovies(pageNo: Int): Resource<MovieResponse> {
        return try {
            val response: Response<MovieResponse> = apiService.getTopRatedMovies(pageNo)
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    Log.d("syedlog","Success: ${result}")
                    Resource.Success(result)
                } ?: Resource.Error("No data found")
            } else {
                Log.d("syedlog","Error: ${response.message()}")
                Resource.Error("Error: ${response.message()}")
            }
        } catch (e: Exception) {
            Log.d("syedlog","Exception: ${e.message}")
            Resource.Error("Exception: ${e.message}")
        }
    }
}