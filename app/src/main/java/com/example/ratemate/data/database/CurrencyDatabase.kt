package com.example.ratemate.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ratemate.data.database.CurrencyEntity
import android.content.Context

@Database(entities = [CurrencyEntity::class], version = 2, exportSchema = false)
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
                    .fallbackToDestructiveMigration() // Automatically recreates the database
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}