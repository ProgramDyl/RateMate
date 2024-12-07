package com.example.ratemate.ui.navigation

import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.ui.components.BottomNavBar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.ratemate.ui.components.TopHeaderBar
import com.example.ratemate.ui.navigation.BottomNavItem
import com.example.ratemate.ui.screens.HomeScreen
import com.example.ratemate.ui.screens.CurrencyScreen
import com.example.ratemate.ui.screens.FavoritesScreen
import com.example.ratemate.ui.screens.ConvertScreen
import com.example.ratemate.ui.screens.SettingsScreen
import com.example.ratemate.ui.screens.CurrencyDataScreen

@Composable
fun RateMateNavHost() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopHeaderBar(navController = navController)
        },
        bottomBar = {
            BottomNavBar(
                items = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Currency,
                    BottomNavItem.Favorites,
                    BottomNavItem.Convert
                ),
                navController = navController,
                onItemClick = { item ->
                    Log.d("BottomNavBar", "Navigating to: ${item.route}")
                    navController.navigate(item.route) {
                        popUpTo("bottom_navigation") { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "bottom_navigation",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Bottom Navigation Graph
            navigation(startDestination = BottomNavItem.Home.route, route = "bottom_navigation") {
                composable(BottomNavItem.Home.route) { HomeScreen() }
                composable(BottomNavItem.Currency.route) {
                    CurrencyScreen(navController = navController)
                }
                composable(BottomNavItem.Favorites.route) {
                    FavoritesScreen(navController = navController)
                }
                composable(BottomNavItem.Convert.route) { ConvertScreen() }
            }

            // Settings Screen
            composable("settings") { SettingsScreen() }

            // Currency Data Screen
            composable(
                route = "currencyData/{currencyCode}",
                arguments = listOf(navArgument("currencyCode") { type = NavType.StringType })
            ) { backStackEntry ->
                val currencyCode = backStackEntry.arguments?.getString("currencyCode")
                CurrencyDataScreen(currencyCode = currencyCode ?: "Unknown")
            }
        }
    }
}


