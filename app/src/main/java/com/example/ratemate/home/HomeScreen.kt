package com.example.ratemate

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingMessage("User")
        Spacer(modifier = Modifier.height(20.dp))
        UserAnalytics(data = listOf("data1", "data2", "data3"))
        Spacer(modifier = Modifier.height(20.dp))
        TravelInfo(info = listOf("TravelInfo1", "TravelInfo2", "TravelInfo3"))
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("data") }) {
            Text(text = "Go to Data Screen")
        }
    }
}

@Composable
fun GreetingMessage(name: String) {
    Text(
        text = "Welcome, $name!",
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun UserAnalytics(data: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        data.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = 4.dp
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun TravelInfo(info: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(info) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = 4.dp
            ) {
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

