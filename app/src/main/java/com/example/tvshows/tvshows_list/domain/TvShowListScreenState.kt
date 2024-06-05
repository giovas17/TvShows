package com.example.tvshows.tvshows_list.domain

import com.example.tvshows.tvshows_list.data.TvShow

data class TvShowListScreenState(
    val tvShows: List<TvShow> = emptyList(),
    val tvShowsOriginal: List<TvShow> = emptyList(),
    val queryToSearch: String = "",
    val searchIsActive: Boolean = false,
    val searchHistory: List<String> = emptyList(),
    val isRefreshing: Boolean = false,
    val isRetrying: Boolean = false,
    val error: String? = null,
)
