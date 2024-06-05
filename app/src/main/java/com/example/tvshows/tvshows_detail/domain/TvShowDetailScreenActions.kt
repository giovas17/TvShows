package com.example.tvshows.tvshows_detail.domain

sealed interface TvShowDetailScreenActions {
    data object Refresh : TvShowDetailScreenActions
}