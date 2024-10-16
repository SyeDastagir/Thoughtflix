package dev.syed.thoughtflix.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.syed.thoughtflix.R
import dev.syed.thoughtflix.data.remote.Movie
import dev.syed.thoughtflix.util.W500
import dev.syed.thoughtflix.util.getImageFullUrl

@Composable
fun SearchGridItemView(movie: Movie) {
    Box(modifier = Modifier
        .fillMaxWidth()) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp)),
            model = movie.posterPath?.getImageFullUrl(W500),
            contentDescription = "Movie Poster",
            contentScale = ContentScale.FillWidth

            )
        Spacer(modifier = Modifier.background(brush = Brush.verticalGradient(listOf(Color.Transparent, Color.Black.copy(0.7f))))
            .fillMaxWidth()
            .height(50.dp)
            .align(Alignment.BottomStart))
        Text(
            text = movie.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart),
        )

        Image(
            painter = painterResource(id = R.drawable.play_large),
            contentDescription = "play",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}