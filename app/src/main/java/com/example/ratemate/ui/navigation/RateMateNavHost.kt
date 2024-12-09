package com.example.ratemate.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ratemate.ui.components.BottomNavBar
import com.example.ratemate.ui.components.TopHeaderBar
import com.example.ratemate.ui.screens.*

@Composable
fun RateMateNavHost() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopHeaderBar(navController = navController) },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(BottomNavItem.Currency.route) { CurrencyScreen(navController, viewModel = viewModel()) }
            composable(BottomNavItem.Favorites.route) { FavoritesScreen(navController, viewModel = viewModel()) }
            composable(BottomNavItem.Convert.route) { ConvertScreen(navController, viewModel = viewModel()) }
            composable(
                route = "currencyData/{currencyCode}",
                arguments = listOf(navArgument("currencyCode") { type = NavType.StringType })
            ) { backStackEntry ->
                val currencyCode = backStackEntry.arguments?.getString("currencyCode")
                if (currencyCode != null) {
                    CurrencyDataScreen(navController = navController, currencyCode = currencyCode, viewModel = viewModel())
                }
            }
        }
    }
}
