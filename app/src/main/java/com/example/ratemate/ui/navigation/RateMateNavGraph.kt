package com.example.ratemate.ui.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ratemate.ui.navigation.BottomNavItem
import com.example.ratemate.ui.screens.HomeScreen
import com.example.ratemate.ui.screens.CurrencyScreen
import com.example.ratemate.ui.screens.FavoritesScreen
import com.example.ratemate.ui.screens.ConvertScreen
import com.example.ratemate.ui.screens.CurrencyDataScreen

//test
@Composable
fun RateMateNavGraph(navController: NavHostController) {
    val application = LocalContext.current.applicationContext as Application

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomNavItem.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomNavItem.Convert.route) {
            ConvertScreen()
        }
        composable(
            route = "currencyData/{currencyCode}",
            arguments = listOf(navArgument("currencyCode") { type = NavType.StringType })
        ) { backStackEntry ->
            val currencyCode = backStackEntry.arguments?.getString("currencyCode")
            if (currencyCode != null) {
                CurrencyDataScreen(currencyCode = currencyCode)
            }
        }
    }
}


