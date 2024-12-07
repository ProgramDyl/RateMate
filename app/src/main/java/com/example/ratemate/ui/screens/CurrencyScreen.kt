package com.example.ratemate.ui.screens

import androidx.compose.runtime.Composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.ui.components.CurrencyCard
import com.example.ratemate.data.api.ExchangeRatesViewModel
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.ratemate.R

@Composable
fun CurrencyScreen(navController: NavHostController, viewModel: ExchangeRatesViewModel = viewModel()) {
    val specificCurrencies = viewModel.specificCurrencies.collectAsState(initial = emptyList())

    LazyColumn {
        specificCurrencies.value.forEach { currency ->
            item {
                CurrencyCard(
                    currencyCode = currency.currencyCode,
                    exchangeRate = currency.rate.toString(),
                    isPositive = true,
                    percentageChange = "+0.00%", // Placeholder
                    isFavorited = currency.isFavorited,
                    onFavoriteClick = {
                        viewModel.toggleFavoriteStatus(currency.currencyCode, !currency.isFavorited)
                    },
                    onCurrencyClick = {
                        navController.navigate("currencyData/${currency.currencyCode}")
                    }
                )
            }
        }
    }
}