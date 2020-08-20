package com.zeroemotion.bfaa_kotlin_tmdb.data.source.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.ApiResponse
import com.zeroemotion.bfaa_kotlin_tmdb.data.source.remote.StatusResponse
import com.zeroemotion.bfaa_kotlin_tmdb.util.AppExecutors
import com.zeroemotion.bfaa_kotlin_tmdb.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDb()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.succes(newData)
                }
            }
        }
    }

    protected fun onFetchFailed() {}
    protected abstract fun loadFromDb(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    appExecutors.diskIO().execute() {
                        saveCallResult(response.body)
                        appExecutors.mainThread().execute() {
                            result.addSource(loadFromDb()) { newData ->
                                result.value = Resource.succes(newData)
                            }
                        }
                    }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
                StatusResponse.EMPTY -> appExecutors.mainThread().execute() {
                    result.addSource(loadFromDb()) { newData ->
                        result.value = Resource.succes(newData)
                    }
                }
            }
        }
    }
    fun asLiveData(): LiveData<Resource<ResultType>> = result
}