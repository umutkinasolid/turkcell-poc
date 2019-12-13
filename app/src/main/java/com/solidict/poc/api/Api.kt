package com.solidict.poc.api

import androidx.lifecycle.LiveData
import com.solidict.poc.vo.ApiResponse
import com.solidict.poc.vo.response_modals.MenuItem
import com.solidict.poc.vo.response_modals.NavBarItem
import com.solidict.poc.vo.response_modals.Subscription
import retrofit2.http.GET


interface Api {

    @GET("subscriptions")
    fun fetchSubscriptions(): LiveData<ApiResponse<List<Subscription>>>

    @GET("menu-items")
    fun fetchMenuItems(): LiveData<ApiResponse<List<MenuItem>>>

    @GET("nav-bar-items")
    fun fetchNavBarItems(): LiveData<ApiResponse<List<NavBarItem>>>

}