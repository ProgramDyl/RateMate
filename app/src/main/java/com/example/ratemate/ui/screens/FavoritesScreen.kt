package com.example.ratemate.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.ui.components.CurrencyCard

@Composable
fun FavoritesScreen(viewModel: ExchangeRatesViewModel = viewModel(), navController: NavHostController) {

    val favoritesWithChange = viewModel.currenciesWithChange.collectAsState(initial = emptyList())

    LazyColumn { // GENERATE CARD FOR EACH FAVORITED CURRENCY IN THE LIST (FILTERING AND KEEPING THOSE WITH FAVORITES = 1)
        favoritesWithChange.value
            .filter { it.isFavorited }
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
