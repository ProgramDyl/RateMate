package com.example.ratemate.data.database

import com.example.ratemate.data.database.CurrencyDao
import com.example.ratemate.data.database.CurrencyEntity
import kotlinx.coroutines.flow.Flow


class CurrencyRepository(private val currencyDao: CurrencyDao) {

    fun getAllCurrencies(): Flow<List<CurrencyEntity>> = currencyDao.getAllCurrencies()

    fun getFavoritedCurrencies(): Flow<List<CurrencyEntity>> = currencyDao.getFavoritedCurrencies()

    fun getSpecificCurrencies(currencyCodes: List<String>): Flow<List<CurrencyEntity>> {
        return currencyDao.getSpecificCurrencies(currencyCodes)
    }


    suspend fun updateFavoriteStatus(currencyCode: String, isFavorited: Boolean) {
        currencyDao.updateFavoriteStatus(currencyCode, isFavorited)
    }

    suspend fun saveCurrencies(currencies: List<CurrencyEntity>) {
        currencyDao.clearAll()
        currencyDao.insertAll(currencies)
    }

    suspend fun isDatabaseEmpty(): Boolean {
        return currencyDao.countCurrencies() == 0
    }
}
