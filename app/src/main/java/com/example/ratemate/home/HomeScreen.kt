package com.example.ratemate.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

        NewsHeadlines(news = listOf(
            newsItem("")
        ))
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
        Text("User Analytics", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Travel Information", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
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
}


@Composable
fun NewsHeadlines(news: List<NewsItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("News of The Day", fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        LazyRow {
            items(news) { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(300.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = item.imageRes),
                                contentDescription = item.headline,
                            )
                        }
                    }
                }
            }
        }
    }
}

data class NewsItem(val headline: String, val imageRes: Int)