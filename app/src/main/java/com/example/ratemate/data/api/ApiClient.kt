package com.example.ratemate.data.api

// https://www.digitalocean.com/community/tutorials/retrofit-android-example-tutorial

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.exchangeratesapi.io/v1/"

    val apiService: ExchangeRatesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRatesApi::class.java)
    }
}