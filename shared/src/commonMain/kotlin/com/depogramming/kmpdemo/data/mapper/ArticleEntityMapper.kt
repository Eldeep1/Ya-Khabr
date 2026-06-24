package com.depogramming.kmpdemo.data.mapper

import com.depogramming.kmpdemo.data.local.ArticleEntity
import com.depogramming.kmpdemo.domain.model.Article

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        id = 0,
        title = this.title,
        description = this.description,
        author = null,
        imageUrl = this.urlToImage,
        url = this.url,
        publishedAt = this.publishedAt,
        content = null
    )
}


fun ArticleEntity.toUIArticle(): Article {
    return Article(
        title = this.title,
        description = this.description ?: "",
        url = this.url ?: "",
        urlToImage = this.imageUrl ?: "",
        publishedAt = this.publishedAt ?: ""
    )
}