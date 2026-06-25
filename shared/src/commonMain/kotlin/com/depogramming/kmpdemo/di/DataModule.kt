package com.depogramming.kmpdemo.di


import com.depogramming.kmpdemo.data.local.ArticleDatabaseService
import com.depogramming.kmpdemo.data.local.getDatabaseBuilder
import com.depogramming.kmpdemo.data.remote.ArticleService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    }
                )
            }
        }
    }
    single {
        ArticleService(get())
    }
    single{
        ArticleDatabaseService(getDatabaseBuilder().build())
    }
}