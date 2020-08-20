package com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote

import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.StatusResponse.*

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)
        fun <T> empty(msg: String, body: T): ApiResponse<T> = ApiResponse(EMPTY, body, msg)
        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }
}