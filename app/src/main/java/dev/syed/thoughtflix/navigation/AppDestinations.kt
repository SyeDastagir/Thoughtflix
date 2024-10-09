package dev.syed.thoughtflix.navigation


sealed class AppDestinations(val route: String) {
    object Authentication : AppDestinations("authentication")
    object SignUp : AppDestinations("signup")
    object Home : AppDestinations("home")
    object HotAndNew : AppDestinations("hotAndNew")
    object Search : AppDestinations("search")
    object Download : AppDestinations("download")
}