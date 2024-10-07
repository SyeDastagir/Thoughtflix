package dev.syed.thoughtflix.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.syed.thoughtflix.feature.download.DownloadScreen
import dev.syed.thoughtflix.feature.home.HomeScreen
import dev.syed.thoughtflix.feature.profile.ProfileScreen
import dev.syed.thoughtflix.feature.search.SearchScreen

@Composable
fun MainNavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Home.route
    ) {
        composable(AppDestinations.Home.route) {
            HomeScreen()
        }
        composable(AppDestinations.Search.route) {
            SearchScreen()
        }
        composable(AppDestinations.Download.route) {
            DownloadScreen()
        }
        composable(AppDestinations.Profile.route) {
            ProfileScreen()
        }
    }
}