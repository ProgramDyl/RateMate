package com.example.ratemate.data.api


// RESPONSE FORMAT
data class ExchangeRatesResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)