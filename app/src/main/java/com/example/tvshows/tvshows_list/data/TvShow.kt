package com.example.tvshows.tvshows_list.data

data class TvShow(
    val id: Int = 0,
    val url: String = "",
    val type: String = "",
    val name: String = "",
    val language: String = "",
    val genres: List<String> = emptyList(),
    val status: String = "",
    val runtime: Int = 0,
    val averageRuntime: Int = 0,
    val premiered: String = "",
    val ended: String = "",
    val officialSite: String = "",
    val schedule: ScheduleTvShow = ScheduleTvShow(),
    val rating: RatingTvShow = RatingTvShow(),
    val weight: Int = 0,
    val image: ImageTvShow = ImageTvShow(),
    val summary: String = "",
    val updated: Long = 0L,
    val _links: LinksTvShow = LinksTvShow(),
    val network: NetworkTvShow = NetworkTvShow(),
)

data class ScheduleTvShow(
    val time: String = "",
    val days: List<String> = emptyList()
)

data class RatingTvShow(val average: Float = 0f)

data class ImageTvShow(val medium: String = "")

data class LinksTvShow(
    val previousepisode: PrevEpisode = PrevEpisode(),
    val self: SelfShow = SelfShow()
)

data class SelfShow(val href: String = "")

data class PrevEpisode(val href: String = "", val name: String = "")

data class NetworkTvShow(
    val id: Int = 0,
    val name: String = "",
    val officialSite: String = "",
    val country: Country = Country(),
)

data class Country(
    val name: String = "",
    val code: String = "",
    val timezone: String = "",
)

