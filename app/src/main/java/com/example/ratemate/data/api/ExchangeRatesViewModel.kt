package com.example.ratemate.data.api

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemate.data.api.ApiClient
import com.example.ratemate.data.database.CurrencyDatabase
import com.example.ratemate.data.database.CurrencyEntity
import com.example.ratemate.data.database.CurrencyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeRatesViewModel(application: Application) : AndroidViewModel(application) {

    private val currencyDao = CurrencyDatabase.getDatabase(application).currencyDao()
    private val repository = CurrencyRepository(currencyDao)

    val currencies = repository.getAllCurrencies() // Flow from database

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchAndSaveExchangeRates() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getExchangeRates()
                val currencyEntities = response.rates.map { (currency, rate) ->
                    CurrencyEntity(currency, rate)
                }
                repository.saveCurrencies(currencyEntities)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}