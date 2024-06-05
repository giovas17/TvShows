package com.example.tvshows.tvshows_detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tvshows.tvshows_detail.domain.TvShowDetailScreenActions
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TvShowDetailScreen(
    tvShowId: Int,
    tvShowName: String,
    onBack: () -> Unit,
) {
    val viewModel: TvShowDetailVM = koinViewModel { parametersOf(tvShowId) }
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = tvShowName) },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
    ) { paddingValues ->
        when {
            state.error != null -> {}
            else -> {
                val refreshing = rememberPullRefreshState(
                    refreshing = state.isRefreshing,
                    onRefresh = { viewModel.perform(TvShowDetailScreenActions.Refresh) })
                Box(
                    modifier = Modifier
                        .padding(paddingValues)
                        .pullRefresh(refreshing)
                        .fillMaxSize()
                ) {
                    TvShowDetailContent(tvShow = state.tvShow)
                    PullRefreshIndicator(
                        refreshing = state.isRefreshing,
                        state = refreshing,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }
    }
}