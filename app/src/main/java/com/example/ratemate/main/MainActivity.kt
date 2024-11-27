package com.example.ratemate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.home.HomeScreen
import com.example.ratemate.data.DataScreen
import com.example.ratemate.ui.theme.RateMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMateTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }  // Comment out BottomNavBar for now
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("data") { DataScreen(navController) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RateMateTheme {
        AppNavigator()
    }
}
