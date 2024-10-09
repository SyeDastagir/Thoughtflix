package dev.syed.thoughtflix.navigation


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.syed.thoughtflix.feature.auth.signin.AuthenticationScreen
import dev.syed.thoughtflix.feature.auth.signup.SignUpScreen
import dev.syed.thoughtflix.feature.dashboard.DashboardScreen

@Composable
fun NavGraph(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.Authentication.route
    ) {
        composable(AppDestinations.Authentication.route) {
            AuthenticationScreen(
                innerPadding = innerPadding,
                onSignIn = { navController.navigate(AppDestinations.Home.route){
                    popUpTo(AppDestinations.Authentication.route) { inclusive = true }
                } },
                onSignUp = { navController.navigate(AppDestinations.SignUp.route) }
            )
        }
        composable(AppDestinations.SignUp.route) {
            SignUpScreen(
                innerPadding = innerPadding,
                onSignUp = { navController.navigate(AppDestinations.Authentication.route) })
        }
        composable(AppDestinations.Home.route) {
            DashboardScreen()
        }
    }
}