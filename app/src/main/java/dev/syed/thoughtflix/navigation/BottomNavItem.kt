package dev.syed.thoughtflix.navigation

import dev.syed.thoughtflix.R

sealed class BottomNavItem(val route: String, val icon: Int, val title: String) {
    object Home : BottomNavItem(AppDestinations.Home.route, R.drawable.home, "Home")
    object HotAndNew : BottomNavItem(AppDestinations.HotAndNew.route, R.drawable.hot_and_new, "New & Hot")
    object Search : BottomNavItem(AppDestinations.Search.route, R.drawable.search, "Search")
    object Download : BottomNavItem(AppDestinations.Download.route, R.drawable.download, "Download")
}