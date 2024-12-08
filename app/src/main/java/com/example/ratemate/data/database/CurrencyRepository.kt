package com.example.ratemate.data.database

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

    // HISTORICAL INSERT
    suspend fun insertHistoricalData(data: List<HistoricalDataEntity>) {
        currencyDao.insertHistoricalData(data)
    }

    // Retrieve historical data for a specific currency
    fun getHistoricalDataForCurrency(currencyCode: String): Flow<List<HistoricalDataEntity>> {
        return currencyDao.getHistoricalDataForCurrency(currencyCode)
    }

    fun getHistoricalDataForDate(date: String): Flow<List<HistoricalDataEntity>> {
        return currencyDao.getHistoricalDataForDate(date)
    }

    // Fetch historical rates for specific currencies
    fun getSpecificHistoricalRates(currencyCodes: List<String>, date: String): Flow<List<HistoricalDataEntity>> {
        return currencyDao.getSpecificHistoricalRates(currencyCodes, date)
    }

    // Delete old historical data
    suspend fun deleteOldData(oldestDate: String) {
        currencyDao.deleteOldData(oldestDate)
    }

}
