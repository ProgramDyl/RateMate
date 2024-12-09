package com.example.ratemate.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ratemate.data.api.ExchangeRatesViewModel

@Composable
fun ConvertScreen(navController: NavHostController, viewModel: ExchangeRatesViewModel = viewModel() ) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Convert Screen Placeholder")
    }
}