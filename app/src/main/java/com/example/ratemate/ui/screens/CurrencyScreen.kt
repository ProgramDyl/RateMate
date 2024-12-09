package com.example.ratemate.ui.screens

import androidx.compose.runtime.Composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.ui.components.CurrencyCard
import com.example.ratemate.data.api.ExchangeRatesViewModel
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.ratemate.R

@Composable
fun CurrencyScreen(
    navController: NavHostController,
    viewModel: ExchangeRatesViewModel = viewModel()
) {
    // Trigger data fetching when the screen is first displayed
    LaunchedEffect(Unit) {
        viewModel.fetchAndSaveExchangeRates()
    }

    // Collect data from the ViewModel
    val currencies = viewModel.currencies.collectAsState(initial = emptyList())

    // Display the data in a LazyColumn
    LazyColumn {
        currencies.value.forEach { currency ->
            item {
                CurrencyCard(
                    //TODO: change flag depending on which currency is being converted
                    flag = painterResource(id = R.drawable.flag_canada), // Replace with dynamic flag resource
                    countryName = currency.currencyCode,
                    exchangeRate = currency.rate.toString(),
                    isPositive = true, // Placeholder for now
                    percentageChange = "+0.00%", // Placeholder for now
                    isFavorited = currency.isFavorited,
                    onFavoriteClick = {
                        viewModel.toggleFavoriteStatus(currency.currencyCode, !currency.isFavorited)
                    }
                )
            }
        }
    }
}
