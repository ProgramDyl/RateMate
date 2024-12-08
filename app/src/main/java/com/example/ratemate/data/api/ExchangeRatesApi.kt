package com.example.ratemate.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// API REQUESTS
interface ExchangeRatesApi {

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("access_key") accessKey: String
    ): ExchangeRatesResponse

    @GET("{date}")
    suspend fun getHistoricalExchangeRates(
        @Path("date") date: String,
        @Query("access_key") accessKey: String
    ): ExchangeRatesResponse

    // API ACCESS KEY
    companion object {
        const val ACCESS_KEY = "baaeaff430616fe496d196d12c6587a0"
    }
}
