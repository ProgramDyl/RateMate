package com.example.ratemate.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ratemate.R
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.ui.components.CurrencyCard

@Composable
fun FavoritesScreen(navController: NavHostController, viewModel: ExchangeRatesViewModel = viewModel()) {
    val favorites = viewModel.favorites.collectAsState(initial = emptyList())

    LazyColumn {
        favorites.value.forEach { currency ->
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