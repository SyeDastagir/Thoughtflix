package dev.syed.thoughtflix.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem(AppDestinations.Home.route, Icons.Default.Home, "Home")
    object Search : BottomNavItem(AppDestinations.Search.route, Icons.Default.Search, "Search")
    object Download : BottomNavItem(AppDestinations.Download.route, Icons.Default.Favorite, "Download")
    object Profile : BottomNavItem(AppDestinations.Profile.route, Icons.Default.Person, "Profile")
}