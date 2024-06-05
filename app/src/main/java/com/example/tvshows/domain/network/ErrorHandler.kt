package com.example.tvshows.domain.network

import android.content.Context
import com.example.tvshows.R
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketException

interface ErrorHandler {
    fun getErrorMessage(e: Throwable): String
}

class AppErrorHandling(private val context: Context): ErrorHandler {
    override fun getErrorMessage(e: Throwable) = when (e) {
        is HttpException -> {
            val obj = JSONObject(e.response()?.errorBody()?.string().orEmpty())
            val errorObj = obj.optJSONObject("error")
            val statusCode = errorObj?.optInt("statusCode", -1)
            val message = errorObj?.optString("message", "")
            context.getString(R.string.error_http_exception, message, statusCode)
        }

        is SocketException -> context.getString(R.string.error_socket_exception)
        else -> context.getString(R.string.error_unknown_exception)
    }

}