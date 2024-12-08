package com.example.ratemate.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ratemate.R
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.data.database.CurrencyWithChange
import com.example.ratemate.ui.components.CurrencyCard

@Composable
fun FavoritesScreen(viewModel: ExchangeRatesViewModel = viewModel(), navController: NavHostController) {
    // Collect the currencies with change and filter for favorites
    val favoritesWithChange = viewModel.currenciesWithChange.collectAsState(initial = emptyList())

    LazyColumn {
        favoritesWithChange.value
            .filter { it.isFavorited } // Show only favorited currencies
            .forEach { currency ->
                item {
                    CurrencyCard(
                        currencyCode = currency.currencyCode,
                        exchangeRate = currency.rate.toString(),
                        isPositive = currency.isPositive,
                        percentageChange = currency.percentageChange,
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
