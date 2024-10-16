package dev.syed.thoughtflix.feature.moviedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun MovieDetailScreen(viewmodel: MovieDetailViewModel = getViewModel()) {
    val navController = rememberNavController()

    LaunchedEffect(Unit) {
        viewmodel.getMovieDetail(1)
    }
    val movies = viewmodel.movie.collectAsStateWithLifecycle()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 8.dp)) {

    }
}