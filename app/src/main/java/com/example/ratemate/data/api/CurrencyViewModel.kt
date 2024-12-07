package com.example.ratemate.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.ratemate.data.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers

class CurrencyViewModel(private val repository: CurrencyRepository) : ViewModel() {

    fun getExchangeRates(base: String) = liveData(Dispatchers.IO) {
        val data = repository.getExchangeRates(base)
        emit(data)
    }
}
