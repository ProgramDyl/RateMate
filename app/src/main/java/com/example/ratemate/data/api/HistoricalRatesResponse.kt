package com.example.ratemate.data.api

data class HistoricalRatesResponse(
    val success: Boolean,
    val rates: Map<String, Map<String, Double>>,
    val start_date: String,
    val end_date: String
)
