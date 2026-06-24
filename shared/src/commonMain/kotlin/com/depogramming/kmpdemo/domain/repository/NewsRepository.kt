package com.depogramming.kmpdemo.domain.repository

import com.depogramming.kmpdemo.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<List<Article>>
    suspend fun addToFavorites(article: Article)
    fun removeFromFavorites(article: Article)
    fun getFavorites(): Flow<List<Article>>
}
