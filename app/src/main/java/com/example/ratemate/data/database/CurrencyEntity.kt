package com.example.ratemate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val currencyCode: String, // E.g., "USD", "EUR"
    val rate: Double // Exchange rate for the currency
)