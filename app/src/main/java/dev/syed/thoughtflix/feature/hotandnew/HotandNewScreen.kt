package dev.syed.thoughtflix.feature.hotandnew

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun HotandNewScreen() {
    val navController = rememberNavController()
    Scaffold(
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding), verticalArrangement = Arrangement.SpaceBetween) {
            Text("HotAndNew Screen")
        }
    }
}