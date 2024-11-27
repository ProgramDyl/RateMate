package com.example.ratemate

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DataScreen(navController: NavHostController) {
    Button(onClick = { navController.navigate("home") }) {
        Text(text = "Go to Home Screen")
    }
}
