package com.depogramming.kmpdemo.data.remote

import kotlinx.serialization.Serializable


@Serializable
data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NetworkArticle>,
)
@Serializable
data class NetworkArticle(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
)
