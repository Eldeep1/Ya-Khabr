package com.depogramming.kmpdemo.di



import com.depogramming.kmpdemo.data.repository.NewsRepositoryImp
import com.depogramming.kmpdemo.domain.repository.NewsRepository
import com.depogramming.kmpdemo.domain.usecase.AddToFavoritesUseCase
import com.depogramming.kmpdemo.domain.usecase.GetFavoritesUseCase
import com.depogramming.kmpdemo.domain.usecase.GetNewsUseCase
import com.depogramming.kmpdemo.domain.usecase.RemoveFromFavoritesUseCase
import org.koin.dsl.module

val domainModule = module {
    single<NewsRepository> {
        NewsRepositoryImp(
            get(), get()
        )
    }

    factory { GetNewsUseCase(get()) }
    factory { AddToFavoritesUseCase(get()) }
    factory { RemoveFromFavoritesUseCase(get()) }
    factory { GetFavoritesUseCase(get()) }
}