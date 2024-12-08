package com.example.ratemate.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Database(entities = [CurrencyEntity::class, HistoricalDataEntity::class], version = 3, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currency_database"
                )
                    .fallbackToDestructiveMigration() // THIS IS ONLY REQUIRED FOR DEVELOPMENT WITH DB CHANGES (can remove in future.. or not)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}