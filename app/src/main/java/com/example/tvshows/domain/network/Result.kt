package com.example.tvshows.domain.network

import java.lang.Exception

sealed class Result<out R> {
    data class Success<out R>(val dataResult: R): Result<R>()
    data class Error(val exception: Exception): Result<Nothing>()
}