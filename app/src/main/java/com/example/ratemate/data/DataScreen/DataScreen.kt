package com.example.ratemate.data

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DataScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        GreetingMessage("User")
        Spacer(modifier = Modifier.height(20.dp))
        HistoricalDataPlaceholder()
        Spacer(modifier = Modifier.height(20.dp))
        OverallPerformancePlaceholder()
        Spacer(modifier = Modifier.height(20.dp))
        TravelSuggestionsPlaceholder()
    }
}

@Composable
fun GreetingMessage(name: String) {
    Text(
        text = "Good Morning, $name!",
        fontSize = 24.sp,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun HistoricalDataPlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Historical Data", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Graph Placeholder", fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Data Point 1", color = androidx.compose.ui.graphics.Color.Gray)
                Text("Data Point 2", color = androidx.compose.ui.graphics.Color.Gray)
                Text("Data Point 3", color = androidx.compose.ui.graphics.Color.Gray)
            }
        }
    }
}


@Composable
fun OverallPerformancePlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Overall Performance", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Circular Chart Placeholder", fontSize = 16.sp, color = androidx.compose.ui.graphics.Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Metric 1", color = androidx.compose.ui.graphics.Color.Gray)
                Text("Metric 2", color = androidx.compose.ui.graphics.Color.Gray)
                Text("Metric 3", color = androidx.compose.ui.graphics.Color.Gray)
            }
        }
    }
}



@Composable
fun TravelSuggestionsPlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Travel Suggestions", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Destination 1", color = androidx.compose.ui.graphics.Color.Gray)
                    Text("Details...", color = androidx.compose.ui.graphics.Color.Gray)
                }
                Column {
                    Text("Destination 2", color = androidx.compose.ui.graphics.Color.Gray)
                    Text("Details...", color = androidx.compose.ui.graphics.Color.Gray)
                }
                Column {
                    Text("Destination 3", color = androidx.compose.ui.graphics.Color.Gray)
                    Text("Details...", color = androidx.compose.ui.graphics.Color.Gray)
                }
            }
        }
    }
}


