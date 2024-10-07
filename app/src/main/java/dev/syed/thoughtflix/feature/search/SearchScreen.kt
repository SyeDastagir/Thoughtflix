package dev.syed.thoughtflix.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.syed.thoughtflix.navigation.BottomNavItem

@Composable
fun SearchScreen() {
    val navController = rememberNavController()
    Scaffold(
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding), verticalArrangement = Arrangement.SpaceBetween) {
            Text("Search Screen")

        }
    }
}