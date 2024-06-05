package com.example.tvshows.tvshows_list.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.tvshows.presentation.common.EmptyView
import com.example.tvshows.tvshows_list.data.TvShow
import com.example.tvshows.tvshows_list.domain.TvShowListScreenActions
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvShowsListScreen(
    modifier: Modifier = Modifier,
    onShowSelected: (TvShow) -> Unit,
) {
    val viewModel: TvShowListVM = koinViewModel()
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "List of Shows") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
            )
        },
    ) { paddingValues ->
        when {
            state.error != null -> {}
            else -> {
                Column(modifier = Modifier.padding(paddingValues)) {
                    var showTvToSearch by remember { mutableStateOf("") }
                    AppSearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        query = showTvToSearch,
                        onQueryChange = { showTvToSearch = it },
                        onSearch = { viewModel.perform(TvShowListScreenActions.Search(it)) },
                        active = state.searchIsActive,
                        onActiveChange = { viewModel.perform(TvShowListScreenActions.ShowOrHideSearch) },
                        onClear = {
                            viewModel.perform(TvShowListScreenActions.ClearSearch(showTvToSearch))
                            showTvToSearch = state.queryToSearch
                        },
                        history = state.searchHistory,
                    )
                    if (state.tvShows.isEmpty()) {
                        EmptyView()
                    } else {
                        TvShowGrid(
                            shows = state.tvShows,
                            onShowClick = { onShowSelected(it) },
                            isRefreshing = state.isRefreshing,
                            onRefresh = { viewModel.perform(TvShowListScreenActions.Refresh) },
                        )
                    }
                }
            }
        }
    }
}