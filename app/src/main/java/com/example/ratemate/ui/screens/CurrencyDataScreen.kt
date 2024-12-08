package com.example.ratemate.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun CurrencyDataScreen(currencyCode: String) {
    // Display the currency-specific data
    Text(
        text = "Details for $currencyCode",
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center
    )
}