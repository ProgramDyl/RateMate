package com.example.ratemate
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ratemate.data.api.ExchangeRatesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ExchangeRatesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // INITIALIZE VIEW MODEL AND RETRIEVE API DATA
        viewModel = ExchangeRatesViewModel(application)
        viewModel.fetchAndSaveExchangeRates() // Fetch data on app startup
        viewModel.fetchAndSaveHistoricalData()

        // OFFLOADING APP RUN ONTO RateMateApp. This maybe could be moved back
        setContent {
            RateMateApp()
        }
    }
}