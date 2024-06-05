package com.example.tvshows.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object TvShowList

@Serializable
data class TvShowDetail(val tvShowId: Int, val tvShowName: String)