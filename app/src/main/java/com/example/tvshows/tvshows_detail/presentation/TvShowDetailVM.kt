package com.example.tvshows.tvshows_detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshows.domain.network.Result
import com.example.tvshows.tvshows_detail.domain.TvShowDetailScreenActions
import com.example.tvshows.tvshows_detail.domain.TvShowDetailScreenState
import com.example.tvshows.tvshows_list.data.TvShow
import com.example.tvshows.tvshows_list.data.TvShowRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TvShowDetailVM(
    private val idTvShow: Int,
    private val repository: TvShowRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(TvShowDetailScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchTvShow()
    }

    private fun fetchTvShow() = viewModelScope.launch {
        _uiState.update { it.copy(isRefreshing = true, error = null) }
        when (val result = repository.getTvShow(idTvShow)) {
            is Result.Error -> handleError(result.exception.message)
            is Result.Success -> handleSuccess(result.dataResult)
        }
    }

    private fun handleSuccess(show: TvShow) {
        _uiState.update {
            it.copy(
                tvShow = show,
                isRefreshing = false,
                error = null,
            )
        }
    }

    private fun handleError(errorMessage: String?) {
        _uiState.update {
            it.copy(
                isRefreshing = false,
                error = errorMessage,
            )
        }
    }

    fun perform(action: TvShowDetailScreenActions) {
        when (action) {
            TvShowDetailScreenActions.Refresh -> fetchTvShow()
        }
    }
}