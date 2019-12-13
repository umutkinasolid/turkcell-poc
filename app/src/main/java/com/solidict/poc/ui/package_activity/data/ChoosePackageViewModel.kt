package com.solidict.poc.ui.package_activity.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.solidict.poc.data_manager.repositories.MainRepository
import com.solidict.poc.util.AbsentLiveData
import com.solidict.poc.vo.Resource
import com.solidict.poc.vo.response_modals.MenuItem
import com.solidict.poc.vo.response_modals.Subscription
import javax.inject.Inject

class ChoosePackageViewModel
@Inject constructor(
    repository: MainRepository
) : ViewModel() {

    private val fetchSubscription = MutableLiveData<Boolean>()

    val subscriptionResult: LiveData<Resource<List<Subscription>>> =
        Transformations.switchMap(fetchSubscription) {
            if (it)
                repository.onFetchSubscriptions()
            else
                AbsentLiveData.create()
        }


    fun onFetchSubscriptions(isFetch: Boolean = true) {
        fetchSubscription.value = isFetch
    }
}