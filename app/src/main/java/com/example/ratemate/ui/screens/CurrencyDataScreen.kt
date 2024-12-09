package com.example.ratemate.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CurrencyDataScreen(currencyCode: String) {
    // Use currencyCode to fetch and display data
    // Placeholder implementation for now
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Data for $currencyCode")
    }
}



//NOTES:
// show currency conversions between chosen currency and 4 popular ones I choose

// click circle on card to go to specific countries data screen.