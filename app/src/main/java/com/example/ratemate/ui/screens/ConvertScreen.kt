package com.example.ratemate.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ratemate.data.api.ExchangeRatesViewModel
import com.example.ratemate.data.database.CurrencyEntity
import kotlinx.coroutines.flow.collect

@Composable
fun ConvertScreen(viewModel: ExchangeRatesViewModel = viewModel()) {
    val currencies by viewModel.currencies.collectAsState(initial = emptyList())
    val selectedCurrency = remember { mutableStateOf<CurrencyEntity?>(null) }
    val amountToConvert = remember { mutableStateOf("") }
    val convertedAmount = remember { mutableStateOf("") }

    var showCurrencyDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Input field for Euro amount
        TextField(
            value = amountToConvert.value,
            onValueChange = { amountToConvert.value = it },
            label = { Text("Amount in Euros") },
            modifier = Modifier.fillMaxWidth()
        )

        // Currency selection button
        Button(
            onClick = { showCurrencyDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Currency: ${selectedCurrency.value?.currencyCode ?: "None"}")
        }

        // Show dialog to select currency
        if (showCurrencyDialog) {
            AlertDialog(
                onDismissRequest = { showCurrencyDialog = false },
                title = { Text("Select a Currency") },
                text = {
                    LazyColumn {
                        items(currencies) { currency ->
                            Text(
                                text = currency.currencyCode,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedCurrency.value = currency
                                        showCurrencyDialog = false // Close dialog after selection
                                    }
                                    .padding(16.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { showCurrencyDialog = false }
                    ) {
                        Text("Close")
                    }
                }
            )
        }

        // Button to perform conversion
        Button(
            onClick = {
                if (selectedCurrency.value != null && amountToConvert.value.isNotEmpty()) {
                    val rate = selectedCurrency.value?.rate ?: 0.0
                    val euroAmount = amountToConvert.value.toDoubleOrNull() ?: 0.0
                    if (euroAmount > 0) {
                        val converted = euroAmount * rate
                        convertedAmount.value = "%.2f".format(converted)
                    } else {
                        convertedAmount.value = "Invalid amount"
                    }
                } else {
                    convertedAmount.value = "Please select a currency and enter a valid amount"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convert")
        }

        // Display the converted amount
        Text(
            text = "Converted Amount: ${convertedAmount.value}",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
