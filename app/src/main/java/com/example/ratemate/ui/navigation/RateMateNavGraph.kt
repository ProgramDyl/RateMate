package com.example.ratemate.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ratemate.ui.navigation.BottomNavItem
import com.example.ratemate.ui.screens.HomeScreen
import com.example.ratemate.ui.screens.CurrencyScreen
import com.example.ratemate.ui.screens.FavoritesScreen
import com.example.ratemate.ui.screens.ConvertScreen
import com.example.ratemate.ui.screens.CurrencyDataScreen
//test
@Composable
fun RateMateNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeScreen()
        }
//        composable(route = BottomNavItem.Currency.route) {
//            CurrencyScreen(onCurrencyClick = { currencyId ->
//                navController.navigate("currencyData/$currencyId")
//            })
//        }
        composable(route = BottomNavItem.Favorites.route) {
            FavoritesScreen()
        }
        composable(route = BottomNavItem.Convert.route) {
            ConvertScreen()
        }
//        composable(
//            route = "currencyData/{currencyId}",
//            arguments = listOf(navArgument("currencyId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val currencyId = backStackEntry.arguments?.getString("currencyId")
//            if (currencyId != null) {
//                CurrencyDataScreen(currencyId = currencyId)
//            }
//        }
    }
}
