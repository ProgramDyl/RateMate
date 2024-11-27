package com.example.ratemate.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingMessage("User")
        Spacer(modifier = Modifier.height(20.dp))
        UserAnalytics(data = listOf("Data 1", "Data 2", "Data 3"))
        Spacer(modifier = Modifier.height(20.dp))
        TravelInfo(info = listOf("Travel Info 1", "Travel Info 2", "Travel Info 3"))
        Spacer(modifier = Modifier.height(20.dp))
        NewsHeadlines(news = listOf("Headline 1", "Headline 2", "Headline 3"))
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
            Text(
                text = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun TravelInfo(info: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        info.forEach { item ->
            Text(
                text = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun NewsHeadlines(news: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(news) { item ->
            Text(
                text = item,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
