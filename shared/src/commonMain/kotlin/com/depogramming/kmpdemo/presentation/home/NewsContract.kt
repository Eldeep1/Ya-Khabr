package com.depogramming.kmpdemo.presentation.home

import com.depogramming.kmpdemo.domain.model.Article

data class NewsState(
    val articles: List<Article> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val favorites: List<Article> = emptyList()
)

sealed interface NewsIntent {
    object LoadNews : NewsIntent
    data class AddToFavorites(val article: Article) : NewsIntent
    data class RemoveFromFavorites(val article: Article) : NewsIntent
    object LoadFavorites : NewsIntent
}