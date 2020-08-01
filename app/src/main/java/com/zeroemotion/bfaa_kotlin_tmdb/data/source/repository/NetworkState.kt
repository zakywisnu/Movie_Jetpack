package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState (val status: Status, val msg:String){
    companion object{
        val LOADED: NetworkState = NetworkState(Status.SUCCESS,"Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Loading")
        val ERROR: NetworkState = NetworkState(Status.FAILED,"Failed to retrieve")

    }
}