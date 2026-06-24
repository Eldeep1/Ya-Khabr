package com.depogramming.kmpdemo.presentation.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.depogramming.kmpdemo.domain.model.Article
import com.depogramming.kmpdemo.presentation.home.NewsIntent
import com.depogramming.kmpdemo.presentation.home.NewsState
import com.depogramming.kmpdemo.presentation.home.viewmodel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsMainScreen(viewModel: NewsViewModel) {
    val state by viewModel.state.collectAsState()
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("KMP News App") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    label = { Text("Feed") },
                    icon = { Icon(Icons.Default.FavoriteBorder, contentDescription = "Feed") }
                )
                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    label = { Text("Favorites") },
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (selectedTab) {
                0 -> NewsFeedPage(
                    state = state,
                    onHeartClick = { article, isFavorite ->
                        if (isFavorite) {
                            viewModel.handleIntent(NewsIntent.RemoveFromFavorites(article))
                        } else {
                            viewModel.handleIntent(NewsIntent.AddToFavorites(article))
                        }
                    }
                )
                1 -> FavoritesPage(
                    state = state,
                    onRemoveClick = { article ->
                        viewModel.handleIntent(NewsIntent.RemoveFromFavorites(article))
                    }
                )
            }
        }
    }
}

@Composable
fun NewsFeedPage(state: NewsState, onHeartClick: (Article, Boolean) -> Unit) {
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (state.errorMessage != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(text = state.errorMessage, color = MaterialTheme.colorScheme.error)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(state.articles) { article ->
                // Check if this specific article exists inside our local Room database list
                val isFavorite = state.favorites.any { it.url == article.url }

                ArticleRow(
                    article = article,
                    isFavorite = isFavorite,
                    onFavoriteToggle = { onHeartClick(article, isFavorite) }
                )
            }
        }
    }
}

@Composable
fun FavoritesPage(state: NewsState, onRemoveClick: (Article) -> Unit) {
    if (state.favorites.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text(text = "No favorite articles yet!", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(state.favorites) { article ->
                ArticleRow(
                    article = article,
                    isFavorite = true,
                    onFavoriteToggle = { onRemoveClick(article) }
                )
            }
        }
    }
}

@Composable
fun ArticleRow(
    article: Article,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                Text(text = article.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = article.description, style = MaterialTheme.typography.bodyMedium, maxLines = 3)
            }
            IconButton(onClick = onFavoriteToggle) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite Toggle",
                    tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}