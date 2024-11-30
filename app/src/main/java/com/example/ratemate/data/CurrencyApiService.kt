package com.example.ratemate.data

import com.example.ratemate.data.model.ExchangeRatesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApiService {
    @GET("{base}")
    suspend fun getExchangeRates(@Path("base") base: String): ExchangeRatesResponse
}
