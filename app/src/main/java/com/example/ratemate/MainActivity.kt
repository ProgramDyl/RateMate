package com.example.ratemate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.ratemate.home.HomeActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Start HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
