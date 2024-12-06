package com.example.ratemate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, "Home")
    object Currency : BottomNavItem("currency", Icons.Filled.Favorite, "Currency")
    object Favorites : BottomNavItem("favorites", Icons.Filled.Favorite, "Favorites")
    object Convert : BottomNavItem("convert", Icons.Filled.Favorite, "Convert")
}
