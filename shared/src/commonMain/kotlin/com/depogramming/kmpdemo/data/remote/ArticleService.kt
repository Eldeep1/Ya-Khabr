package com.depogramming.kmpdemo.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess

class ArticleService(private val client: HttpClient = NetworkClient.client) {

    private val url = "https://newsapi.org/v2/top-headlines?"
    private val country = "us"
    private val category = "business"
    private val apiKey = "46e3d967b98642759078e95ce30ba933"

    suspend fun fetchArticles(): List<NetworkArticle> {
        val result = client.get(urlString = url) {
            parameter("country", country)
            parameter("category", category)
            parameter("apiKey", apiKey)
        }

        return if (result.status.isSuccess()) {
            result.body<ArticleResponse>().articles
        } else {
            emptyList()
        }
    }
}
