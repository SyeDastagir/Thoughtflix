package dev.syed.thoughtflix.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.syed.thoughtflix.R
import dev.syed.thoughtflix.data.remote.Movie
import dev.syed.thoughtflix.util.W500
import dev.syed.thoughtflix.util.getImageFullUrl

@Composable
fun SearchItemView(movie: Movie) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            modifier = Modifier.width(120.dp).height(72.dp).clip(RoundedCornerShape(8.dp)),
            model = movie.backdropPath?.getImageFullUrl(W500),
            contentDescription = "Movie Poster",

        )
        Text(
            text = movie.title,
            color = Color.White,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(8.dp).weight(1f),
        )
        Image(modifier = Modifier.size(32.dp), painter = painterResource(id = R.drawable.play_large), contentDescription = "play")
    }
}