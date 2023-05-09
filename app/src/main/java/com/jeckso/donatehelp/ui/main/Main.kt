package com.jeckso.donatehelp.ui.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jeckso.donatehelp.ui.details.DonateDetails
import com.jeckso.donatehelp.ui.donates.Donates
import com.jeckso.donatehelp.ui.theme.highlightGreen

@Composable
fun DonateHelpMainScreen() {
    val navController = rememberNavController()

    val colors = MaterialTheme.colorScheme
    val systemUiController = rememberSystemUiController()

    var statusBarColor by remember { mutableStateOf(colors.primary) }
    var navigationBarColor by remember { mutableStateOf(colors.primary) }

    val animatedStatusBarColor by animateColorAsState(
        targetValue = statusBarColor,
        animationSpec = tween()
    )
    val animatedNavigationBarColor by animateColorAsState(
        targetValue = navigationBarColor,
        animationSpec = tween()
    )

    NavHost(navController = navController, startDestination = NavScreen.Home.route) {
        composable(NavScreen.Home.route) {
            Donates(
                viewModel = hiltViewModel(),
                selectDonate = {
                    navController.navigate("${NavScreen.DonateDetails.route}/$it")
                }
            )

            LaunchedEffect(Unit) {
                statusBarColor = highlightGreen
                navigationBarColor = highlightGreen
            }
        }
        composable(
            route = NavScreen.DonateDetails.routeWithArgument,
            arguments = listOf(
                navArgument(NavScreen.DonateDetails.argument0) { type = NavType.LongType }
            )
        ) { backStackEntry ->
            val donateId =
                backStackEntry.arguments?.getLong(NavScreen.DonateDetails.argument0)
                    ?: return@composable

            DonateDetails(donateId = donateId, viewModel = hiltViewModel()) {
                navController.navigateUp()
            }

            LaunchedEffect(Unit) {
                statusBarColor = Color.Transparent
                navigationBarColor = colors.background
            }
        }
    }
    LaunchedEffect(animatedStatusBarColor, animatedNavigationBarColor) {
        systemUiController.setStatusBarColor(animatedStatusBarColor)
        systemUiController.setNavigationBarColor(animatedNavigationBarColor)
    }

}

sealed class NavScreen(val route: String) {

    object Home : NavScreen("Home")

    object DonateDetails : NavScreen("DonateDetails") {

        const val routeWithArgument: String = "DonateDetails/{donateId}"

        const val argument0: String = "donateId"
    }
}
