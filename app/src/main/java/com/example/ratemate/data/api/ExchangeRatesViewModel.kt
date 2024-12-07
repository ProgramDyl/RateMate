package com.example.ratemate.data.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemate.data.api.ExchangeRatesResponse
//import com.example.ratemate.data.api.ExchangeRatesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExchangeRatesViewModel : ViewModel() {
    private val _exchangeRates = MutableStateFlow<Map<String, Double>>(emptyMap())
    val exchangeRates: StateFlow<Map<String, Double>> get() = _exchangeRates

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    fun fetchExchangeRates() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getExchangeRates()
                _exchangeRates.value = response.rates
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}