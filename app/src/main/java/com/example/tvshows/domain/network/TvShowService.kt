package com.example.tvshows.domain.network

import com.example.tvshows.tvshows_list.data.TvShow
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowService {
    @GET("/shows")
    suspend fun getTvShows() : List<TvShow>

    @GET("/shows/{id_tvShow}")
    suspend fun getTvShow(@Path("id_tvShow") idTvShow: Int) : TvShow
}