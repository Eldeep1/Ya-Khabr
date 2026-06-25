package com.depogramming.kmpdemo

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.depogramming.kmpdemo.presentation.home.view.NewsMainScreen
import com.depogramming.kmpdemo.presentation.home.viewmodel.NewsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {

        val viewModel: NewsViewModel = koinViewModel()

        NewsMainScreen(viewModel = viewModel)
    }
}