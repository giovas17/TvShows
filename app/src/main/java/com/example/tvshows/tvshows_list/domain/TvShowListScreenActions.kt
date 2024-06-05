package com.example.tvshows.tvshows_list.domain

sealed interface TvShowListScreenActions {

    data object Refresh : TvShowListScreenActions
    data object Retry : TvShowListScreenActions
    data object ShowOrHideSearch : TvShowListScreenActions
    data class ClearSearch(val query: String) : TvShowListScreenActions
    data class Search(val query: String) : TvShowListScreenActions
    data class Error(val errorMessage: String) : TvShowListScreenActions
}