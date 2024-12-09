package com.example.ratemate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.ui.components.CurrencyCard
import com.murgupluoglu.flagkit.FlagKit
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import com.example.ratemate.ui.components.CustomText
import com.example.ratemate.viewmodel.NewsViewModel
import com.example.ratemate.ui.components.styleSheets.StyledNewsCard
import com.example.ratemate.utils.currencyToCountryCode

@Composable
fun HomeScreen(navController: NavHostController, viewModel: ExchangeRatesViewModel = viewModel(), newsViewModel: NewsViewModel = viewModel()) {
    val newsItems = newsViewModel.newsItems.value

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CustomText(text = "Popular")

            val currencies = viewModel.currencies.collectAsState(initial = emptyList())
            LazyColumn {
                currencies.value.forEach { currency ->
                    if (currency.currencyCode in listOf("CAD", "USD", "CNY", "IDR")) {
                        item {
                            val context = LocalContext.current
                            val flagResource = FlagKit.getResId(context, currencyToCountryCode(currency.currencyCode))

                            val currentRate = currency.rate
                            val previousRate = getPreviousRate(currency.currencyCode) // Implement this function to fetch the previous rate

                            val percentageChange = calculatePercentageChange(currentRate, previousRate)

                            CurrencyCard(
                                flag = painterResource(id = flagResource),
                                countryName = currency.currencyCode,
                                exchangeRate = currency.rate.toString(),
                                isPositive = percentageChange.startsWith("+"),
                                percentageChange = percentageChange,
                                isFavorited = currency.isFavorited,
                                onFavoriteClick = {
                                    // Navigate to CurrencyDataScreen with currency code
                                    navController.navigate("currencyData/${currency.currencyCode}")
                                }
                            )
                        }
                    }
                }
            }

            // Add the news section using StyledNewsCard
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(newsItems) { item ->
                    StyledNewsCard(newsItem = item)
                }
            }
        }
    }
}

fun calculatePercentageChange(currentRate: Double, previousRate: Double): String {
    val change = ((currentRate - previousRate) / previousRate) * 100
    return if (change >= 0) {
        "+%.2f%%".format(change)
    } else {
        "%.2f%%".format(change)
    }
}

// Placeholder function for fetching the previous rate
fun getPreviousRate(currencyCode: String): Double {
    // Implement logic to fetch the previous rate for the given currency code
    return 1.00 // Placeholder value, replace with actual previous rate
}
