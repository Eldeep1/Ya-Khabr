package com.depogramming.kmpdemo.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.depogramming.kmpdemo.domain.model.Article
import com.depogramming.kmpdemo.domain.usecase.AddToFavoritesUseCase
import com.depogramming.kmpdemo.domain.usecase.GetFavoritesUseCase
import com.depogramming.kmpdemo.domain.usecase.GetNewsUseCase
import com.depogramming.kmpdemo.domain.usecase.RemoveFromFavoritesUseCase
import com.depogramming.kmpdemo.presentation.home.NewsIntent
import com.depogramming.kmpdemo.presentation.home.NewsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    val state: StateFlow<NewsState> = _state.asStateFlow()

    init {
        // Automatically start listening to local favorites and remote news
        handleIntent(NewsIntent.LoadFavorites)
        handleIntent(NewsIntent.LoadNews)
    }

    fun handleIntent(intent: NewsIntent) {
        when (intent) {
            is NewsIntent.LoadNews -> loadNews()
            is NewsIntent.LoadFavorites -> observeFavorites()
            is NewsIntent.AddToFavorites -> addToFavorites(intent.article)
            is NewsIntent.RemoveFromFavorites -> removeFromFavorites(intent.article)
        }
    }

    private fun loadNews() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getNewsUseCase()
                .catch { error -> _state.update { it.copy(isLoading = false, errorMessage = error.message) } }
                .collect { articles -> _state.update { it.copy(isLoading = false, articles = articles) } }
        }
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase()
                .catch { error -> _state.update { it.copy(errorMessage = error.message) } }
                .collect { favoriteArticles ->
                    _state.update { it.copy(favorites = favoriteArticles) }
                }
        }
    }

    private fun addToFavorites(article: Article) {
        viewModelScope.launch {
            addToFavoritesUseCase(article)
        }
    }

    private fun removeFromFavorites(article: Article) {
        viewModelScope.launch {
            removeFromFavoritesUseCase(article)
        }
    }
}