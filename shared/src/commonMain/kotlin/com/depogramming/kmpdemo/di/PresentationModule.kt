package com.depogramming.kmpdemo.di


import com.depogramming.kmpdemo.presentation.home.viewmodel.NewsViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory {
        NewsViewModel(
            getNewsUseCase = get(),
            addToFavoritesUseCase = get(),
            getFavoritesUseCase = get(),
            get(),

        )
    }
}