package com.example.ratemate.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey val currencyCode: String,
    val rate: Double,
    var isFavorited: Boolean = false // Optional column for favorites
)