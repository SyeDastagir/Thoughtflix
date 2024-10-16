package dev.syed.thoughtflix.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import dev.syed.thoughtflix.components.SearchItemView
import dev.syed.thoughtflix.components.TTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(viewModel: SearchViewmodel = getViewModel()) {
    val query by viewModel.query.collectAsStateWithLifecycle()
    val movies = viewModel.movies.collectAsLazyPagingItems()
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(start = 8.dp, end = 8.dp, top = 32.dp, bottom = 8.dp)
    ) {
        TTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            keyboardActions = KeyboardActions(onSearch = {
                keyboardController?.hide()
                viewModel.updateQuery(query) }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            onValueChange = { viewModel.updateQuery(it) },
            label = "Search Movies",
            isError = false,
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(count = movies.itemCount) {
                val movie = movies[it]
                if (movie != null) {
                    SearchItemView(movie)
                }
            }
        }
    }
}