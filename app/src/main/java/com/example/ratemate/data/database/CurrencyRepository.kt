package com.example.ratemate.data.database

import kotlinx.coroutines.flow.Flow

class CurrencyRepository(private val currencyDao: CurrencyDao) {

    // GET ALL CURRENCIES
    fun getAllCurrencies(): Flow<List<CurrencyEntity>> = currencyDao.getAllCurrencies()

    // GET FAVORITED CURRENCIES
    fun getFavoritedCurrencies(): Flow<List<CurrencyEntity>> = currencyDao.getFavoritedCurrencies()

    // GET SPECIFIC CURRENCY
    fun getSpecificCurrencies(currencyCodes: List<String>): Flow<List<CurrencyEntity>> {
        return currencyDao.getSpecificCurrencies(currencyCodes)
    }

    // FAVORITES UPDATE
    suspend fun updateFavoriteStatus(currencyCode: String, isFavorited: Boolean) {
        currencyDao.updateFavoriteStatus(currencyCode, isFavorited)
    }

    // CLEARS AND SAVES NEW CURRENT DATA
    suspend fun saveCurrencies(currencies: List<CurrencyEntity>) {
        currencyDao.clearAll()
        currencyDao.insertAll(currencies)
    }

    // CHECK IF DB EMPTY
    suspend fun isDatabaseEmpty(): Boolean {
        return currencyDao.countCurrencies() == 0
    }

    // HISTORICAL INSERT
    suspend fun insertHistoricalData(data: List<HistoricalDataEntity>) {
        currencyDao.insertHistoricalData(data)
    }

    // GET HISTORICAL FOR SPECIFIC CURRENCY
    fun getHistoricalDataForCurrency(currencyCode: String): Flow<List<HistoricalDataEntity>> {
        return currencyDao.getHistoricalDataForCurrency(currencyCode)
    }

    //GET HISTORICAL DATA FOR SPECIFIC DATE
    fun getHistoricalDataForDate(date: String): Flow<List<HistoricalDataEntity>> {
        return currencyDao.getHistoricalDataForDate(date)
    }

    // GET HISTORICAL DATA FOR SPECIFIC CURRENCIES (LIST)
    fun getSpecificHistoricalRates(currencyCodes: String, date: String): Flow<List<HistoricalDataEntity>> {
        return currencyDao.getSpecificHistoricalRates(currencyCodes, date)
    }

    // DELETE HISTORICAL
    suspend fun deleteOldData(oldestDate: String) {
        currencyDao.deleteOldData(oldestDate)
    }



}
