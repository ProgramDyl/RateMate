package com.example.ratemate.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ratemate.data.repository.CurrencyRepository

class CurrencyViewModelFactory(
    private val repository: CurrencyRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            return CurrencyViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return create(modelClass)
    }
}
