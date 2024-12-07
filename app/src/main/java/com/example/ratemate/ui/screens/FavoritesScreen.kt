package com.example.ratemate.ui.screens

import android.app.Application
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.R
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.data.api.ExchangeRatesViewModelFactory
import com.example.ratemate.ui.components.CurrencyCard

@Composable
fun FavoritesScreen(viewModel: ExchangeRatesViewModel = viewModel()) {
    val favorites = viewModel.favorites.collectAsState(initial = emptyList())

    LazyColumn {
        favorites.value.forEach { currency ->
            item {
                CurrencyCard(
                    flag = painterResource(id = R.drawable.flag_canada), // Replace with actual flag resource
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
