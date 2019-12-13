package com.solidict.poc.api

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.solidict.poc.util.AbsentLiveData
import com.solidict.poc.vo.*

abstract class NetworkBoundWrapper<ResultType, RequestType> {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        fetchFromNetwork()
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork() {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            when (response) {
                is ApiSuccessResponse -> {
                    result.addSource(loadFromApi(response.body)) { newData ->
                        setValue(Resource.success(newData))
                    }
                }
                is ApiEmptyResponse -> {
                    onFetchEmpty()
                }
                is ApiErrorResponse -> {
                    result.addSource(fail()) { nullData ->
                        setValue(Resource.error(response.errorMessage, nullData))
                    }
                    onFetchFailed()
                }
            }
        }
    }

    private fun fail(): LiveData<ResultType> {
        return AbsentLiveData.create()
    }

    protected open fun onFetchFailed() {
    }

    protected open fun onFetchEmpty() {
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>


    @MainThread
    protected abstract fun loadFromApi(data: RequestType): LiveData<ResultType>

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
}