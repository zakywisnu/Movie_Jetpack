package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

enum class Status{
    LOADING,
    SUCCESS,
    FAILED
}

class NetworkState<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): NetworkState<T> = NetworkState(Status.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): NetworkState<T> = NetworkState(Status.FAILED, data, msg)

        fun <T> loading(data: T?): NetworkState<T> = NetworkState(Status.LOADING, data, null)
    }
}
