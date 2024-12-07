package com.example.ratemate.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("latest?access_key=baaeaff430616fe496d196d12c6587a0")
    suspend fun getExchangeRates(): ExchangeRatesResponse
}
