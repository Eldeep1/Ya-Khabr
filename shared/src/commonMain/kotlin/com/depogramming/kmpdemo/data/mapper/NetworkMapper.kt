package com.depogramming.kmpdemo.data.mapper

import com.depogramming.kmpdemo.data.remote.NetworkArticle
import com.depogramming.kmpdemo.domain.model.Article

fun NetworkArticle.toUIArticle() = Article(

    title=this.title ?: "sad",
    description=this.description ?:"sad",
    url=this.url?:"sad",
    urlToImage=this.urlToImage?:"https://png.pngtree.com/png-vector/20250623/ourmid/pngtree-cute-duck-meme-sticker-vector-illustration-png-image_16581842.png",
    publishedAt=this.publishedAt?:"sad",
)