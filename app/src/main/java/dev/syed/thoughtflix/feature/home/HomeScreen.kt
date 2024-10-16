package dev.syed.thoughtflix.feature.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import dev.syed.thoughtflix.R
import dev.syed.thoughtflix.data.remote.MovieResponse
import dev.syed.thoughtflix.util.Resource
import dev.syed.thoughtflix.util.W500
import dev.syed.thoughtflix.util.getImageFullUrl
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(innerPaddingValues: PaddingValues, viewmodel: HomeViewmodel = koinViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewmodel.getPopularMovies(1)
        viewmodel.getTopRatedMovies(1)
        viewmodel.getNowPlayingMovies(1)
        viewmodel.getUpcomingMovies(1)
    }
    val popular = viewmodel.popularMovies.collectAsStateWithLifecycle()
    val nowPlaying = viewmodel.nowPlayingMovies.collectAsStateWithLifecycle()
    val topRated = viewmodel.topRatedMovies.collectAsStateWithLifecycle()
    val upcoming = viewmodel.upcomingMovies.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(innerPaddingValues)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState())
    ) {
        when (popular.value) {
            is Resource.Error -> {
                Log.d("syedlog", "error while fetch popular movies: ${popular.value}")
            }

            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Resource.Success -> {
                val movieResponse = (popular.value as Resource.Success).data
                MainContent(movieResponse)
            }

            null -> {

            }
        }
        when (nowPlaying.value) {
            is Resource.Error -> { }
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Resource.Success -> {
                nowPlaying.value?.data?.let {
                    MovieBackdropRow("Now Playing", it)
                }
            }

            null -> {}
        }
        when (topRated.value) {
            is Resource.Error -> {}
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Resource.Success -> {
                topRated.value?.data?.let {
                    MoviePosterRow("Top Rated", it)
                }
            }

            null -> {}
        }
        when (upcoming.value) {
            is Resource.Error -> {}
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is Resource.Success -> {
                upcoming.value?.data?.let {
                    MoviePosterRow("Upcoming", it)
                }
            }

            null -> {}
        }
    }

}

@Composable
fun MainContent(movieResponse: MovieResponse?) {
    val firstMovie = movieResponse?.results?.firstOrNull()
    firstMovie?.let { movie ->
        Box(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = movie.posterPath?.getImageFullUrl(W500),
                contentDescription = "Movie Poster"
            )
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(0.7f),
                                Color.Transparent
                            )
                        )
                    )
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.padding(
                    top = 32.dp,
                    start = 8.dp,
                    end = 8.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.n_logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(55.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "search",
                    modifier = Modifier
                        .size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.account_user),
                    contentDescription = "account",
                    modifier = Modifier
                        .size(24.dp)
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(0.7f)
                            )
                        )
                    )
                    .padding(top = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movie.title,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "play",
                            tint = Color.White
                        )
                        Text(text = "My List", color = Color.White)
                    }
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors()
                            .copy(containerColor = Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "play",
                            tint = Color.Black
                        )
                        Text(text = "Play", color = Color.Black)
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "play",
                            tint = Color.White
                        )
                        Text(text = "Info", color = Color.White)
                    }
                }
            }


        }
        MoviePosterRow("Popular Movies", movieResponse)
    }
}

@Composable
private fun MoviePosterRow(title: String, movieResponse: MovieResponse) {
    Text(
        text = title,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.titleMedium,
        color = Color.White
    )
    LazyRow {
        items(movieResponse.results.size) {
            Box(
                modifier = Modifier
                    .width(140.dp)
                    .height(180.dp)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = movieResponse.results[it].posterPath?.getImageFullUrl(W500),
                    contentDescription = "Movie Poster"
                )
                Image(
                    painter = painterResource(id = R.drawable.play_large),
                    contentDescription = "Play Icon",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(32.dp) // Adjust the size as needed
                )
            }
        }
    }
}

@Composable
private fun MovieBackdropRow(title: String, movieResponse: MovieResponse) {
    Text(
        text = title,
        modifier = Modifier.padding(8.dp),
        style = MaterialTheme.typography.titleMedium,
        color = Color.White
    )
    LazyRow {
        items(movieResponse.results.size) {
            val movie = movieResponse.results[it]
            Box(
                modifier = Modifier
                    .width(240.dp)
                    .height(140.dp)
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = movie.backdropPath?.getImageFullUrl(W500),
                    contentDescription = "Movie Poster"
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f)
                                )
                            )
                        )
                )
                Text(
                    text = movie.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(PaddingValues())
}