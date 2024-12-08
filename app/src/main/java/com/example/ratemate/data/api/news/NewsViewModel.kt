package com.example.ratemate.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ratemate.data.api.news.NewsItem
import com.example.ratemate.data.api.news.RetrofitInstance
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val newsItems = mutableStateOf<List<NewsItem>>(emptyList())

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getTopHeadlines()
                Log.d("NewsViewModel", "Response: ${response.articles}")
                newsItems.value = response.articles.map { article ->
                    NewsItem(
                        imageUrl = article.urlToImage ?: "",
                        title = article.title,
                        description = article.description ?: ""
                    )
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "Error fetching news: ${e.message}")
            }
        }
    }
}