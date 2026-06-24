package com.depogramming.kmpdemo.data.mapper

import com.depogramming.kmpdemo.data.remote.NetworkArticle
import com.depogramming.kmpdemo.domain.model.Article

fun NetworkArticle.toUIArticle() = Article(
    title=this.title,
    description=this.description,
    url=this.url,
    urlToImage=this.urlToImage,
    publishedAt=this.publishedAt,
)