package com.example.tvshows.tvshows_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tvshows.tvshows_list.data.TvShow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TvShowGrid(
    modifier: Modifier = Modifier,
    shows: List<TvShow>,
    onShowClick: (TvShow) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
) {
    val refreshing = rememberPullRefreshState(refreshing = isRefreshing, onRefresh = onRefresh)
    Box(modifier = modifier.pullRefresh(refreshing)) {
        LazyVerticalGrid(
            modifier = Modifier.padding(12.dp).fillMaxSize(),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(shows) { show ->
                TvShowListItem(tvShow = show) { onShowClick(it) }
            }
        }
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = refreshing,
            Modifier.align(Alignment.TopCenter)
        )
    }

}

@Preview
@Composable
fun PreviewShowsGrid() {
    TvShowGrid(
        shows = listOf(
            TvShow(name = "Test 1"),
            TvShow(name = "Test 2"),
            TvShow(name = "Test 3")
        ),
        onShowClick = {},
        isRefreshing = false,
        onRefresh = {}
    )
}