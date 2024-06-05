package com.example.tvshows.tvshows_list.domain

import com.example.tvshows.tvshows_list.data.TvShow

class TvShowUseCases {

    fun searchShowTv(searchQuery: String, shows: List<TvShow>): List<TvShow> =
        shows.filter { it.name.lowercase().contains(searchQuery.lowercase()) }
}