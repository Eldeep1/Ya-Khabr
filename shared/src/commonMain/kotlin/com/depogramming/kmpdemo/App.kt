package com.depogramming.kmpdemo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel // 1. Add this import!
import com.depogramming.kmpdemo.data.local.ArticleDatabaseService
import com.depogramming.kmpdemo.data.local.getDatabaseBuilder
import com.depogramming.kmpdemo.data.remote.ArticleService
import com.depogramming.kmpdemo.data.repository.NewsRepositoryImp
import com.depogramming.kmpdemo.domain.usecase.AddToFavoritesUseCase
import com.depogramming.kmpdemo.domain.usecase.GetFavoritesUseCase
import com.depogramming.kmpdemo.domain.usecase.GetNewsUseCase
import com.depogramming.kmpdemo.domain.usecase.RemoveFromFavoritesUseCase
import com.depogramming.kmpdemo.presentation.home.view.NewsMainScreen
import com.depogramming.kmpdemo.presentation.home.viewmodel.NewsViewModel

@Composable
fun App() {
    MaterialTheme {
        // Leave the use cases as 'remember' since they are stateless
        val repository = remember {
            NewsRepositoryImp(
                articleService = ArticleService(),
                articleDatabaseService = ArticleDatabaseService(getDatabaseBuilder().build())
            )
        }
        val getNewsUseCase = remember { GetNewsUseCase(repository) }
        val addToFavoritesUseCase = remember { AddToFavoritesUseCase(repository) }
        val getFavoritesUseCase = remember { GetFavoritesUseCase(repository) }
        val removeFromFavoritesUseCase = remember { RemoveFromFavoritesUseCase(repository) }

        // 2. Change 'remember' to 'viewModel' here
        val viewModel = viewModel {
            NewsViewModel(
                getNewsUseCase = getNewsUseCase,
                addToFavoritesUseCase = addToFavoritesUseCase,
                getFavoritesUseCase = getFavoritesUseCase,
                removeFromFavoritesUseCase = removeFromFavoritesUseCase
            )
        }

        NewsMainScreen(viewModel = viewModel)
    }
}