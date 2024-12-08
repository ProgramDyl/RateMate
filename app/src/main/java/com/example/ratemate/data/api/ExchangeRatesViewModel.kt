package com.example.ratemate.data.api

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemate.data.database.CurrencyDatabase
import com.example.ratemate.data.database.CurrencyEntity
import com.example.ratemate.data.database.CurrencyRepository
import com.example.ratemate.data.database.CurrencyWithChange
import com.example.ratemate.data.database.HistoricalDataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ExchangeRatesViewModel(application: Application) : AndroidViewModel(application) {

    private val currencyDao = CurrencyDatabase.getDatabase(application).currencyDao()
    private val repository = CurrencyRepository(currencyDao)
//    private val specificCurrencyCodes = listOf("USD", "GBP", "JPY", "CAD", "CHF", "AUD", "CNY", "HKD", "NZD", "SEK", "KRW", "SGD", "NOK", "INR", "MXN")

    // GET ALL CURRENCIES FROM BACKEND (CURRENT RATES)
    val currencies: Flow<List<CurrencyEntity>> = repository.getAllCurrencies()

    // GET CURRENT RATES FOR SPECIFIC CURRENCIES (LISTED ABOVE)
//    val currentRates = repository.getSpecificCurrencies(specificCurrencyCodes)

//    // HISTORICAL RATES FOR SPECIFIC(LISTED ABOVE) CURRENCIES (1 MONTH)
            // NOTE: The following function will require the above specificCurrencyCodes list to be converted to a Flow or have the method parameters change.
//    private val specificHistoricalRates: Flow<List<HistoricalDataEntity>> = repository.getSpecificHistoricalRates(
//        specificCurrencyCodes,
//        getOneMonthPriorDate()
//    )

    // HISTORICAL RATES FOR ALL CURRENCIES (1 MONTH)
    private val historicalRates: Flow<List<HistoricalDataEntity>> = repository.getHistoricalDataForDate(getOneMonthPriorDate())

    // GET ALL CURRENT RATES (CALLED ON APP START-UP)
    fun fetchAndSaveExchangeRates() {
        viewModelScope.launch {
            try {
                if (repository.isDatabaseEmpty()) {
                    val response = ApiClient.apiService.getExchangeRates(ExchangeRatesApi.ACCESS_KEY)
                    Log.d("ExchangeRateViewModel", "Getting Rates: ${response}")
                    val currencyEntities = response.rates.map { (currencyCode, rate) ->
                        CurrencyEntity(currencyCode = currencyCode, rate = rate, isFavorited = false)
                    }
                    repository.saveCurrencies(currencyEntities)
                }
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
            }
        }
    }

    // GET ALL HISTORICAL RATES (1D, 1WK, 1MO, 1YR) (CALLED ON APP START-UP)
    fun fetchAndSaveHistoricalData() {
        viewModelScope.launch {
            try {
                val dates = getHistoricalDates()
                val historicalData = mutableListOf<HistoricalDataEntity>()

                for (date in dates) {
                    // FETCH HISTORICAL RATES FROM API
                    val response = ApiClient.apiService.getHistoricalExchangeRates(date, ExchangeRatesApi.ACCESS_KEY)

                    // ITERATE OVER RESPONSE AND MAP TO DATA ENTITY
                    response.rates.forEach { (currencyCode, rate) ->
                        historicalData.add(
                            HistoricalDataEntity(
                                currencyCode = currencyCode,
                                date = date,
                                rate = rate
                            )
                        )
                    }
                }

                // SAVE HISTORICAL DATA TO DATABASE
                repository.insertHistoricalData(historicalData)
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }

    // COMBINE & CALCULATE RATE DIFFERENCE
    val currenciesWithChange: Flow<List<CurrencyWithChange>> = combine(currencies, historicalRates) { currentList, historicalList ->
        currentList.filter { current ->
            historicalList.any { it.currencyCode == current.currencyCode }
        }.map { current ->
            val historicalRate = historicalList.find { it.currencyCode == current.currencyCode }?.rate

            val percentageChange = if (historicalRate != null) {
                ((current.rate - historicalRate) / historicalRate) * 100
            } else 0.0

            CurrencyWithChange(
                currencyCode = current.currencyCode,
                rate = current.rate,
                isFavorited = current.isFavorited,
                isPositive = percentageChange >= 0,
                percentageChange = "%.2f%%".format(percentageChange)
            )
        }
    }

    // FAVORITES TOGGLE
    fun toggleFavoriteStatus(currencyCode: String, isFavorited: Boolean) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(currencyCode, isFavorited)
        }
    }
}
