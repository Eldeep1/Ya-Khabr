package com.depogramming.kmpdemo.data.local

import kotlinx.coroutines.flow.Flow

class ArticleDatabaseService(private val database: AppDatabase) {

    private val articleDao = database.articleDao()

    suspend fun saveArticles(articles: List<ArticleEntity>) {
        articleDao.insertArticles(articles)
    }

    fun getArticles(): Flow<List<ArticleEntity>> {
        return articleDao.getAllArticles()
    }

    suspend fun deleteArticle(article: ArticleEntity) {
        articleDao.deleteArticle(article)
    }
}