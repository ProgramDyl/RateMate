package com.example.ratemate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val currencyCode: String,
    val rate: Double,
    var isFavorited: Boolean = false // Optional column for favorites
)


@Entity(tableName = "historical_data")
data class HistoricalDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val currencyCode: String,
    val date: String, // The date for the historical data
    val rate: Double
)

data class CurrencyWithChange(
    val currencyCode: String,
    val rate: Double,
    val isFavorited: Boolean,
    val isPositive: Boolean,
    val percentageChange: String
)