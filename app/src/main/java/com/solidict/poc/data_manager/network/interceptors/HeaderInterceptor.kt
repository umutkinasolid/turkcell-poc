package com.solidict.poc.data_manager.network.interceptors

import okhttp3.Interceptor
import javax.inject.Inject
import javax.inject.Singleton

private const val ACCEPT_HEADER = "Accept"
private const val JSON_TYPE = "application/json"

@Singleton
class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(
            ACCEPT_HEADER,
            JSON_TYPE
        ).build()
        return chain.proceed(request)
    }
}