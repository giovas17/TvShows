package com.example.tvshows.domain.di

import com.example.tvshows.tvshows_detail.presentation.TvShowDetailVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tvShowDetailScreenModule = module {
    viewModel { params -> TvShowDetailVM(params.get(), get()) }
}