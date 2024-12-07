package com.example.ratemate

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.screens.HomeScreen
import com.example.ratemate.data.DataScreen
import com.example.ratemate.ui.theme.UserViewModel
import com.example.ratemate.home.CurrencyViewModel
import com.example.ratemate.screens.Screen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route,
    userViewModel: UserViewModel,
    currencyViewModel: CurrencyViewModel
) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomeScreen(currencyViewModel, userViewModel) }
            composable(Screen.Data.route) { DataScreen(userViewModel) }
            // Add more composable destinations here as needed
        }
    }
}
