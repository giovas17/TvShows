package com.example.tvshows.tvshows_list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshows.domain.network.Result
import com.example.tvshows.tvshows_list.data.TvShow
import com.example.tvshows.tvshows_list.domain.TvShowListScreenActions
import com.example.tvshows.tvshows_list.domain.TvShowListScreenState
import com.example.tvshows.tvshows_list.data.TvShowRepository
import com.example.tvshows.tvshows_list.domain.TvShowUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TvShowListVM(
    private val repository: TvShowRepository,
    private val useCases: TvShowUseCases,
) : ViewModel() {

    private val _uiState: MutableStateFlow<TvShowListScreenState> = MutableStateFlow(
        TvShowListScreenState()
    )
    val uiState = _uiState.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() = viewModelScope.launch {
        _uiState.update { it.copy(isRefreshing = true, error = null) }
        when (val result = repository.getTvShowsList()) {
            is Result.Error -> handleError(result.exception.message)
            is Result.Success -> handleSuccess(result.dataResult)
        }
    }

    private fun handleSuccess(shows: List<TvShow>) {
        _uiState.update {
            it.copy(
                tvShows = shows,
                tvShowsOriginal = shows,
                isRefreshing = false,
                isRetrying = false,
                error = null,
            )
        }
    }

    private fun handleError(errorMessage: String?) {
        _uiState.update {
            it.copy(
                isRefreshing = false,
                isRetrying = false,
                error = errorMessage,
            )
        }
    }

    fun perform(action: TvShowListScreenActions) {
        when (action) {
            is TvShowListScreenActions.Error -> _uiState.update { it.copy(error = action.errorMessage) }
            TvShowListScreenActions.Refresh -> refresh()
            TvShowListScreenActions.Retry -> {
                _uiState.update { it.copy(isRetrying = true) }
                refresh()
            }

            TvShowListScreenActions.ShowOrHideSearch -> _uiState.update { it.copy(searchIsActive = !it.searchIsActive) }
            is TvShowListScreenActions.ClearSearch -> {
                if (action.query.isEmpty()) {
                    _uiState.update { it.copy(searchIsActive = !it.searchIsActive) }
                    refresh()
                } else {
                    _uiState.update { it.copy(queryToSearch = "") }
                }
            }

            is TvShowListScreenActions.Search -> {
                val history = ArrayList(uiState.value.searchHistory).apply { add(action.query) }
                _uiState.update {
                    it.copy(
                        tvShows = useCases.searchShowTv(action.query, it.tvShowsOriginal),
                        searchIsActive = false,
                        queryToSearch = "",
                        searchHistory = history
                    )
                }
            }
        }
    }
}