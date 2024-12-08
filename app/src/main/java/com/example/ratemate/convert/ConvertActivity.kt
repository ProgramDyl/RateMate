package com.example.ratemate.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun ConvertScreen() {
    // Remember the text input state for both fields
    val firstInput = remember { mutableStateOf(TextFieldValue("")) }
    val secondInput = remember { mutableStateOf(TextFieldValue("")) }

    // Convert input values to numbers and add them together
    val firstNumber = firstInput.value.text.toFloatOrNull() ?: 0f
    val secondNumber = secondInput.value.text.toFloatOrNull() ?: 0f
    val result = firstNumber + secondNumber

    // Format the result to 2 decimal places
    val formattedResult = String.format("%.2f", result)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            // First input field
            TextField(
                value = firstInput.value,
                onValueChange = { firstInput.value = it },
                label = { Text("First Input") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            // Second input field
            TextField(
                value = secondInput.value,
                onValueChange = { secondInput.value = it },
                label = { Text("Second Input") },
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            )

            // Display the result with 2 decimal places
            Text(
                text = "Result: $formattedResult",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
