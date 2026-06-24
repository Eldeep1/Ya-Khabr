package com.depogramming.kmpdemo.data.repository

import com.depogramming.kmpdemo.data.local.ArticleDatabaseService
import com.depogramming.kmpdemo.data.mapper.toArticleEntity
import com.depogramming.kmpdemo.data.mapper.toUIArticle
import com.depogramming.kmpdemo.data.remote.ArticleService
import com.depogramming.kmpdemo.domain.model.Article
import com.depogramming.kmpdemo.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class NewsRepositoryImp (val articleService: ArticleService, val articleDatabaseService: ArticleDatabaseService) : NewsRepository {

    override fun getNews(): Flow<List<Article>> {
        return flow {
            val result = articleService.fetchArticles()
            val mappedArticles = result.map { it.toUIArticle() }
            emit(mappedArticles)
        }
    }

    override suspend fun addToFavorites(article: Article) {
        articleDatabaseService.saveArticles(listOf(article.toArticleEntity()))
    }

    override fun removeFromFavorites(article: Article) {
        CoroutineScope(Dispatchers.IO).launch {
            articleDatabaseService.deleteArticle(article.toArticleEntity())
        }
    }

    override fun getFavorites(): Flow<List<Article>> {
        return articleDatabaseService.getArticles().map { entities ->
            entities.map { it.toUIArticle() }
        }
    }
}

