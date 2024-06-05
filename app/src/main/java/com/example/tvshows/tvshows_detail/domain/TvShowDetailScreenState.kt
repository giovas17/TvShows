package com.example.tvshows.tvshows_detail.domain

import com.example.tvshows.tvshows_list.data.TvShow

data class TvShowDetailScreenState(
    val tvShow: TvShow = TvShow(),
    val isRefreshing: Boolean = false,
    val error: String? = null,
)
