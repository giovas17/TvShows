package com.example.tvshows.tvshows_list.data

import com.example.tvshows.domain.network.ErrorHandler
import com.example.tvshows.domain.network.TvShowService
import com.example.tvshows.domain.network.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

interface TvShowRepository {
    suspend fun getTvShowsList(
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Result<List<TvShow>>

    suspend fun getTvShow(
        id: Int,
        ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Result<TvShow>
}

class TvShowRepositoryImpl(
    private val client: TvShowService,
    private val errorHandler: ErrorHandler,
) : TvShowRepository {
    override suspend fun getTvShowsList(ioDispatcher: CoroutineDispatcher): Result<List<TvShow>> =
        withContext(ioDispatcher) {
            try {
                Result.Success(client.getTvShows())
            } catch (e: Throwable) {
                Result.Error(Exception(errorHandler.getErrorMessage(e)))
            }
        }

    override suspend fun getTvShow(id: Int, ioDispatcher: CoroutineDispatcher): Result<TvShow> =
        withContext(ioDispatcher) {
            try {
                Result.Success(client.getTvShow(id))
            } catch (e: Throwable) {
                Result.Error(Exception(errorHandler.getErrorMessage(e)))
            }
        }

}