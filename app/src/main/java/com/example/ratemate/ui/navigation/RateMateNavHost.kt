package com.example.ratemate.ui.navigation

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.ui.components.BottomNavBar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
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
            BottomNavBar(navController = navController)
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
                composable(BottomNavItem.Currency.route) { CurrencyScreen() }
                composable(BottomNavItem.Favorites.route) { FavoritesScreen() }
                composable(BottomNavItem.Convert.route) { ConvertScreen() }
            }

            // Settings Screen
            composable("settings") { SettingsScreen() }
        }
    }
}

