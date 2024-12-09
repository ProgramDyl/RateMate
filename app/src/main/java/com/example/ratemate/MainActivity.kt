package com.example.ratemate

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import com.example.ratemate.data.api.ExchangeRatesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: ExchangeRatesViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize SharedPreferences for theme settings
        sharedPreferences = getSharedPreferences("settings_pref", MODE_PRIVATE)
        applySavedTheme() // Apply saved theme preference

        // Initialize ViewModel and retrieve API data
        viewModel = ExchangeRatesViewModel(application)
        viewModel.fetchAndSaveExchangeRates() // Fetch data on app startup
        viewModel.fetchAndSaveHistoricalData()

        // Offloading app run onto RateMateApp
        setContent {
            RateMateApp()
        }
    }

    private fun applySavedTheme() {
        // Check saved theme preference and apply
        val isDarkMode = sharedPreferences.getBoolean("isDarkMode", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}
