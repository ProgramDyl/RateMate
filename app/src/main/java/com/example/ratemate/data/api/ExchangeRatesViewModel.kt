package com.example.ratemate.data.api

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ratemate.data.api.ApiClient
import com.example.ratemate.data.database.CurrencyDatabase
import com.example.ratemate.data.database.CurrencyEntity
import com.example.ratemate.data.database.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExchangeRatesViewModel(application: Application) : AndroidViewModel(application) {

    private val currencyDao = CurrencyDatabase.getDatabase(application).currencyDao()
    private val repository = CurrencyRepository(currencyDao)

    // Expose all currencies as a Flow
    val currencies: Flow<List<CurrencyEntity>> = repository.getAllCurrencies()
    val favorites: Flow<List<CurrencyEntity>> = repository.getFavoritedCurrencies()

    private val _selectedCurrencyRate = MutableStateFlow<Double?>(null)
    val selectedCurrencyRate: StateFlow<Double?> = _selectedCurrencyRate.asStateFlow()

    private val _historicalRates = MutableStateFlow<Map<String, Double>?>(null)
    val historicalRates: StateFlow<Map<String, Double>?> = _historicalRates.asStateFlow()

    fun toggleFavoriteStatus(currencyCode: String, isFavorited: Boolean) {
        viewModelScope.launch {
            repository.updateFavoriteStatus(currencyCode, isFavorited)
        }
    }

    fun fetchAndSaveExchangeRates() {
        viewModelScope.launch {
            try {
                if (repository.isDatabaseEmpty()) {
                    val response = ApiClient.apiService.getExchangeRates()
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

    fun fetchExchangeRateForCurrency(currencyCode: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getExchangeRates()
                val rate = response.rates[currencyCode]
                _selectedCurrencyRate.value = rate
            } catch (e: Exception) {
                // Handle error
                println("Error fetching exchange rate: $e")
                e.printStackTrace()
            }
        }
    }


    fun fetchHistoricalRates(currencyCode: String, startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getHistoricalRates(
                    accessKey = "baaeaff430616fe496d196d12c6587a0",
                    startDate = startDate,
                    endDate = endDate,
                    symbols = currencyCode
                )
                println("Historical rates response: $response")
                _historicalRates.value = response.rates[currencyCode]
            } catch (e: Exception) {
                println("Error fetching historical rates: $e")
                e.printStackTrace()
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