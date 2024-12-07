package com.example.ratemate.data.database

import com.example.ratemate.data.database.CurrencyDao
import com.example.ratemate.data.database.CurrencyEntity
import kotlinx.coroutines.flow.Flow

class CurrencyRepository(private val currencyDao: CurrencyDao) {

    fun getAllCurrencies(): Flow<List<CurrencyEntity>> = currencyDao.getAllCurrencies()

    suspend fun saveCurrencies(currencies: List<CurrencyEntity>) {
        currencyDao.clearAll() // Optional: Clear the table first
        currencyDao.insertAll(currencies)
    }
}