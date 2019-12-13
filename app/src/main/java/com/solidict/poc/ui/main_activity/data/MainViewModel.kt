package com.solidict.poc.ui.main_activity.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.solidict.poc.data_manager.repositories.MainRepository
import com.solidict.poc.util.AbsentLiveData
import com.solidict.poc.vo.Resource
import com.solidict.poc.vo.response_modals.MenuItem
import com.solidict.poc.vo.response_modals.NavBarItem
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    repository: MainRepository
) : ViewModel() {


    private val fetchMenuItems = MutableLiveData<Boolean>()

    val menuItemsResult: LiveData<Resource<List<MenuItem>>> =
        Transformations.switchMap(fetchMenuItems) {
            if (it)
                repository.onFetchMenuItems()
            else
                AbsentLiveData.create()
        }

    fun onFetchMenuItems(isFetch: Boolean = true) {
        fetchMenuItems.value = isFetch
    }


    private val navBarItems=MutableLiveData<Boolean>()

    val navBarItemsResult: LiveData<Resource<List<NavBarItem>>> =
        Transformations.switchMap(navBarItems) {
            if (it)
                repository.onFetchNavBar()
            else
                AbsentLiveData.create()
        }

    fun onNavBarItems(isFetch: Boolean = true) {
        navBarItems.value = isFetch
    }


}