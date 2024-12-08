package com.example.ratemate.data.api

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    // Expose all currencies as a Flow
    val currencies: Flow<List<CurrencyEntity>> = repository.getAllCurrencies()

    val favorites: Flow<List<CurrencyEntity>> = repository.getFavoritedCurrencies()

    private val specificCurrencyCodes = listOf("USD", "GBP", "JPY", "CAD", "CHF", "AUD", "CNY", "HKD", "NZD", "SEK", "KRW", "SGD", "NOK", "INR", "MXN")

    val currentRates = repository.getSpecificCurrencies(specificCurrencyCodes)


    // Historical rates for specific currencies (1 day prior)
    private val historicalRates: Flow<List<HistoricalDataEntity>> = repository.getSpecificHistoricalRates(
        specificCurrencyCodes,
        getOneMonthPriorDate()
    )

    // FAVORITES TOGGLE
    fun toggleFavoriteStatus(currencyCode: String, isFavorited: Boolean) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(currencyCode, isFavorited)
        }
    }

    //
    fun getSpecificCurrencies(currencyCodes: List<String>): Flow<List<CurrencyEntity>> {
        return repository.getSpecificCurrencies(currencyCodes)
    }

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
                    // Fetch historical data for the date
                    val response = ApiClient.apiService.getHistoricalExchangeRates(date, ExchangeRatesApi.ACCESS_KEY)

                    // Iterate over the response rates and map them to HistoricalDataEntity
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

                // Save all the historical data to the database
                repository.insertHistoricalData(historicalData)
            } catch (e: Exception) {
                // Handle errors (e.g., log or display a message)
            }
        }
    }




    // Combine current and historical rates
    val currenciesWithChange: Flow<List<CurrencyWithChange>> = combine(currentRates, historicalRates) { currentList, historicalList ->
        currentList.map { current ->
            val historicalRate = historicalList.find { it.currencyCode == current.currencyCode }?.rate

            val percentageChange = if (historicalRate != null) {
                ((current.rate - historicalRate) / historicalRate) * 100
            } else 0.0 // Default to 0.0% if no historical data

            CurrencyWithChange(
                currencyCode = current.currencyCode,
                rate = current.rate,
                isFavorited = current.isFavorited,
                isPositive = percentageChange >= 0,
                percentageChange = "%.2f%%".format(percentageChange) // Always display 0.00% if no change
            )
        }
    }

    val historicalData = repository.getHistoricalDataForDate(getHistoricalDates()[0])

    fun calculatePercentageChange(): Flow<List<CurrencyWithChange>> {
        return combine(currencies, historicalData) { currentRates, historicalRates ->
            currentRates.map { current ->
                val previousRate = historicalRates.find { it.currencyCode == current.currencyCode }?.rate ?: 0.0
                val percentageChange = if (previousRate != 0.0) {
                    ((current.rate - previousRate) / previousRate) * 100
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
    }

}


class ExchangeRatesViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeRatesViewModel::class.java)) {
            return ExchangeRatesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}