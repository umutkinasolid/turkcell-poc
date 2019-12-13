package com.solidict.poc.data_manager.repositories

import androidx.lifecycle.LiveData
import com.solidict.poc.api.Api
import com.solidict.poc.api.NetworkBoundWrapper
import com.solidict.poc.util.InitialLiveData
import com.solidict.poc.vo.ApiResponse
import com.solidict.poc.vo.Resource
import com.solidict.poc.vo.response_modals.MenuItem
import com.solidict.poc.vo.response_modals.NavBarItem
import com.solidict.poc.vo.response_modals.Subscription
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val api: Api
) {


    fun onFetchSubscriptions(): LiveData<Resource<List<Subscription>>> {
        return object : NetworkBoundWrapper<List<Subscription>, List<Subscription>>() {
            override fun loadFromApi(data: List<Subscription>): LiveData<List<Subscription>> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<List<Subscription>>> =
                api.fetchSubscriptions()
        }.asLiveData()
    }

    fun onFetchMenuItems(): LiveData<Resource<List<MenuItem>>> {
        return object : NetworkBoundWrapper<List<MenuItem>, List<MenuItem>>() {
            override fun loadFromApi(data: List<MenuItem>): LiveData<List<MenuItem>> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<List<MenuItem>>> =
                api.fetchMenuItems()
        }.asLiveData()
    }


    fun onFetchNavBar(): LiveData<Resource<List<NavBarItem>>> {
        return object : NetworkBoundWrapper<List<NavBarItem>, List<NavBarItem>>() {
            override fun loadFromApi(data: List<NavBarItem>): LiveData<List<NavBarItem>> {
                return InitialLiveData.create(data)
            }

            override fun createCall(): LiveData<ApiResponse<List<NavBarItem>>> =
                api.fetchNavBarItems()
        }.asLiveData()
    }
}