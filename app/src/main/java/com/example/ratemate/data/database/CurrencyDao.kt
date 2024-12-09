package com.example.ratemate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    // CURRENT
    @Query("SELECT * FROM currencies")
    fun getAllCurrencies(): Flow<List<CurrencyEntity>>

    @Query("SELECT * FROM currencies WHERE isFavorited = 1")
    fun getFavoritedCurrencies(): Flow<List<CurrencyEntity>>
    @Query("SELECT * FROM currencies WHERE currencyCode IN (:currencyCodes)")
    fun getSpecificCurrencies(currencyCodes: List<String>): Flow<List<CurrencyEntity>>

    @Query("UPDATE currencies SET isFavorited = :isFavorited WHERE currencyCode = :currencyCode")
    suspend fun updateFavoriteStatus(currencyCode: String, isFavorited: Boolean): Int

    @Query("SELECT COUNT(*) FROM currencies")
    suspend fun countCurrencies(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CurrencyEntity>)

    @Query("DELETE FROM currencies")
    suspend fun clearAll()


    // HISTORICAL

    @Query("SELECT * FROM historical_data WHERE currencyCode = :currencyCode")
    fun getHistoricalDataForCurrency(currencyCode: String): Flow<List<HistoricalDataEntity>>

    @Query("SELECT * FROM historical_data WHERE date = :date")
    fun getHistoricalDataForDate(date: String): Flow<List<HistoricalDataEntity>>

    @Query("SELECT * FROM historical_data WHERE currencyCode IN (:currencyCodes) AND date = :date")
    fun getSpecificHistoricalRates(
        currencyCodes: List<String>,
        date: String
    ): Flow<List<HistoricalDataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistoricalData(data: List<HistoricalDataEntity>)

    @Query("DELETE FROM historical_data WHERE date < :oldestDate")
    suspend fun deleteOldData(oldestDate: String) // Optional cleanup
}

