package com.example.ratemate.screens

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Data : Screen("data", "Data")
}
