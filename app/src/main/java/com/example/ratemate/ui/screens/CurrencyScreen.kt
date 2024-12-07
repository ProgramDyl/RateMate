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
    val exchangeRates = viewModel.exchangeRates.collectAsState()
    val error = viewModel.error.collectAsState()

    // Fetch data when the screen loads
    viewModel.fetchExchangeRates()

    if (exchangeRates.value.isNotEmpty()) {
        LazyColumn {
            exchangeRates.value.forEach { (currency, rate) ->
                item {
                    CurrencyCard(
                        flag = painterResource(id = R.drawable.flag_canada), // Replace with actual flag resources
                        countryName = currency,
                        exchangeRate = rate.toString(),
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
