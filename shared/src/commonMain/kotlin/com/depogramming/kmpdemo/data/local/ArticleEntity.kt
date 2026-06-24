package com.depogramming.kmpdemo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String?,
    val author: String?,
    val imageUrl: String?,
    val url: String?,
    val publishedAt: String?,
    val content: String?
)
