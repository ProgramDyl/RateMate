package com.example.ratemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.ui.theme.RateMateTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ExchangeRatesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    RateMateApp()
                }
            }
        }
    }
}
