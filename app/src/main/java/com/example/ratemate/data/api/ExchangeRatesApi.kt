package com.example.ratemate.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("access_key") accessKey: String,
        @Query("base") base: String = "EUR", // Default base currency is EUR
        @Query("symbols") symbols: String? = null // Optional symbols parameter
    ): Response<ExchangeRatesResponse>
}
