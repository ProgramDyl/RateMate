package com.example.ratemate.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.ui.components.CurrencyCard
import com.example.ratemate.data.api.ExchangeRatesViewModel
import androidx.compose.ui.res.painterResource
import com.example.ratemate.R

@Composable
fun CurrencyScreen(viewModel: ExchangeRatesViewModel = viewModel()) {
    val currencies = viewModel.currencies.collectAsState(initial = emptyList())
    val error = viewModel.error.collectAsState()

    // Fetch data on screen load
    viewModel.fetchAndSaveExchangeRates()

    if (currencies.value.isNotEmpty()) {
        LazyColumn {
            currencies.value.forEach { currencyEntity ->
                item {
                    CurrencyCard(
                        flag = painterResource(id = R.drawable.flag_canada), // Replace with actual flags
                        countryName = currencyEntity.currencyCode,
                        exchangeRate = currencyEntity.rate.toString(),
                        percentageChange = "+0.00%", // Placeholder for now
                        isPositive = true,
                        isFavorited = false,
                        onFavoriteClick = {}
                    )
                }
            }
        }
    } else if (error.value != null) {
        // Display error
        Text(text = "Error: ${error.value}")
    }
}
