package com.example.ratemate.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ratemate.R
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.murgupluoglu.flagkit.FlagKit
import com.example.ratemate.utils.currencyToCountryCode
import com.example.ratemate.ui.*
import com.example.ratemate.ui.components.styleSheets.bodyTextStyle
import com.example.ratemate.ui.components.styleSheets.cardElevation
import com.example.ratemate.ui.components.styleSheets.cardModifier
import com.example.ratemate.ui.components.styleSheets.sectionSpacer
import com.example.ratemate.ui.components.styleSheets.titleTextStyle

@Composable
fun CurrencyDataScreen(navController: NavHostController, currencyCode: String, viewModel: ExchangeRatesViewModel = viewModel()) {
    val currentExchangeRate by viewModel.selectedCurrencyRate.collectAsState()
    val historicalRates by viewModel.historicalRates.collectAsState()

    LaunchedEffect(currencyCode) {
        viewModel.fetchExchangeRateForCurrency(currencyCode)
        viewModel.fetchHistoricalRates(currencyCode, "2023-01-01", "2023-12-31") // Example date range
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        CurrencyHeader(currencyCode)
        Spacer(modifier = sectionSpacer)
        if (currentExchangeRate == null) {
            LoadingIndicator()
        } else {
            CurrentExchangeRate(currentExchangeRate)
        }
        Spacer(modifier = sectionSpacer)
        HistoricalData(historicalRates)
        Spacer(modifier = sectionSpacer)
        OverallPerformance(currencyCode)
    }
}

@Composable
fun CurrencyHeader(currencyCode: String) {
    val context = LocalContext.current
    val flagResource = FlagKit.getResId(context, currencyToCountryCode(currencyCode))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Currency Data for $currencyCode",
            style = titleTextStyle
        )
        Image(
            painter = painterResource(id = flagResource),
            contentDescription = "$currencyCode Flag",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun CurrentExchangeRate(currentRate: Double?) {
    Card(
        modifier = cardModifier,
        elevation = cardElevation
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Current Exchange Rate", style = titleTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = currentRate?.toString() ?: "Loading...",
                style = bodyTextStyle
            )
        }
    }
}

@Composable
fun HistoricalData(historicalRates: Map<String, Double>?) {
    Card(
        modifier = cardModifier,
        elevation = cardElevation
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Historical Data", style = titleTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            if (historicalRates != null) {
                // Replace with actual graph when data is available
                Image(
                    painter = painterResource(id = R.drawable.baseline_auto_graph_24),
                    contentDescription = "Graph Placeholder",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            } else {
                // Placeholder for circular chart
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_auto_graph_24),
                        contentDescription = "Graph Placeholder",
                        modifier = Modifier.size(100.dp)
                    )
                }
                Text("Loading...", style = bodyTextStyle)
            }
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun OverallPerformance(currencyCode: String) {
    Card(
        modifier = cardModifier,
        elevation = cardElevation
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Overall Performance", style = titleTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Circular Chart Placeholder", style = bodyTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Metric 1", style = bodyTextStyle)
                Text("Metric 2", style = bodyTextStyle)
                Text("Metric 3", style = bodyTextStyle)
            }
        }
    }
}
