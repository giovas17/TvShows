package com.example.tvshows.domain.di

import com.example.tvshows.tvshows_list.data.TvShowRepository
import com.example.tvshows.tvshows_list.data.TvShowRepositoryImpl
import com.example.tvshows.tvshows_list.domain.TvShowUseCases
import com.example.tvshows.tvshows_list.presentation.TvShowListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val tvShowScreenModule = module {
    factory { TvShowRepositoryImpl(get(), get()) } bind TvShowRepository::class
    factory { TvShowUseCases() }
    viewModel { TvShowListVM(get(), get()) }
}