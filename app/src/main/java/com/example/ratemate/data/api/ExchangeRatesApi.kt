package com.example.ratemate.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("latest")
    suspend fun getExchangeRates(
        @Query("access_key") accessKey: String // Dynamically pass the API key
    ): ExchangeRatesResponse

    @GET("{date}")
    suspend fun getHistoricalExchangeRates(
        @Path("date") date: String,           // The date for historical data
        @Query("access_key") accessKey: String // Dynamically pass the API key
    ): ExchangeRatesResponse

    companion object {
        const val ACCESS_KEY = "baaeaff430616fe496d196d12c6587a0" // Define your API key as a constant
    }
}
