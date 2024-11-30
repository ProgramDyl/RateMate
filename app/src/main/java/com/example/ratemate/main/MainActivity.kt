package com.example.ratemate.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.ratemate.AppNavHost
import com.example.ratemate.data.database.AppDatabase
import com.example.ratemate.data.repository.UserRepository
import com.example.ratemate.ui.theme.RateMateTheme
import com.example.ratemate.ui.theme.UserViewModel
import com.example.ratemate.ui.theme.UserViewModelFactory
import com.example.ratemate.home.CurrencyViewModel
import com.example.ratemate.home.CurrencyViewModelFactory
import com.example.ratemate.data.CurrencyApiService
import com.example.ratemate.data.RetrofitInstance
import com.example.ratemate.data.repository.CurrencyRepository

class MainActivity : ComponentActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // UserViewModel setup
        val database = AppDatabase.getDatabase(this)
        val userRepository = UserRepository(database.userDao())
        val userViewModelFactory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        // CurrencyViewModel setup
        val apiService = RetrofitInstance.retrofit.create(CurrencyApiService::class.java)
        val currencyRepository = CurrencyRepository(apiService)
        val currencyViewModelFactory = CurrencyViewModelFactory(currencyRepository)
        currencyViewModel = ViewModelProvider(this, currencyViewModelFactory).get(CurrencyViewModel::class.java)

        setContent {
            RateMateTheme {
                AppNavHost(userViewModel = userViewModel, currencyViewModel = currencyViewModel)
            }
        }
    }
}
