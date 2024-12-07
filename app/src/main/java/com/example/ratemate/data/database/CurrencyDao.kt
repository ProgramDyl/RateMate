package com.example.ratemate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ratemate.data.database.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

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
}

