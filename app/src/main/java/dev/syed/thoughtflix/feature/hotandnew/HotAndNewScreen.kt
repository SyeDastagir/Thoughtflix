package dev.syed.thoughtflix.feature.hotandnew

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import dev.syed.thoughtflix.components.SearchGridItemView
import org.koin.androidx.compose.getViewModel

@Composable
fun HotAndNewScreen(hotAndNewViewmodel: HotAndNewViewmodel = getViewModel()) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        hotAndNewViewmodel.getTrendingMovies(1)
    }
    val movies = hotAndNewViewmodel.trendingMovies.collectAsLazyPagingItems()

    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(count = movies.itemCount) {
                val movie = movies[it]
                if (movie != null) {
                    SearchGridItemView(movie)
                }
            }
        }
    }
}