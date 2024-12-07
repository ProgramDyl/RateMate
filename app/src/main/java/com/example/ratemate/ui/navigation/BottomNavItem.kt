package com.example.ratemate.ui.navigation

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.ratemate.R

sealed class BottomNavItem(val route: String, val iconRes: Int, val label: String) {
    object Home : BottomNavItem("home", R.drawable.icon_home, "Home")
    object Currency : BottomNavItem("currency", R.drawable.icon_euro, "Currency")
    object Favorites : BottomNavItem("favorites", R.drawable.favorite_filled , "Favorites")
    object Convert : BottomNavItem("convert", R.drawable.icon_calculator, "Convert")
}
