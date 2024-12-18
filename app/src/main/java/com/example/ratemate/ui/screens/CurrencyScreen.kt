package com.example.ratemate.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.ui.components.CurrencyCard
import com.example.ratemate.data.api.ExchangeRatesViewModel
import androidx.navigation.NavHostController


@Composable
fun CurrencyScreen(viewModel: ExchangeRatesViewModel = viewModel(), navController: NavHostController) {
    val currenciesWithChange = viewModel.currenciesWithChange.collectAsState(initial = emptyList())

    LazyColumn { // GENERATE CARD FOR EACH CURRENCY IN THE LIST
        currenciesWithChange.value.forEach { currency ->
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