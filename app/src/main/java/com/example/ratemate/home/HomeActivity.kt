package com.example.ratemate.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.ratemate.ui.theme.RateMateTheme
import com.example.ratemate.R


class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RateMateTheme {
                Surface {
                    HomeScreen("Dylan", R.drawable.ic_user_placeholder)
                }
            }
        }
    }
}
