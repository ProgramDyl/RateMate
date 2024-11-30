package com.example.ratemate.data.repository

import com.example.ratemate.data.CurrencyApiService
import com.example.ratemate.data.model.ExchangeRatesResponse

class CurrencyRepository(private val apiService: CurrencyApiService) {

    suspend fun getExchangeRates(base: String): ExchangeRatesResponse {
        return apiService.getExchangeRates(base)
    }
}
