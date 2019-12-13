package com.solidict.poc.ui.package_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.solidict.poc.R
import com.solidict.poc.base.BaseActivity
import com.solidict.poc.ui.package_activity.data.ChoosePackageViewModel
import com.solidict.poc.ui.package_activity.adapter.PackageItemListAdapter
import com.solidict.poc.vo.Status
import com.solidict.poc.vo.response_modals.Subscription
import kotlinx.android.synthetic.main.activity_choose_package.*
import timber.log.Timber
import javax.inject.Inject

class ChoosePackageActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: ChoosePackageViewModel by viewModels {
        viewModelFactory
    }

    private val subscriptions = ArrayList<Subscription>()
    private lateinit var subscriptionsAdapter: PackageItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_package)
        setupRecyclerView()

        viewModel.onFetchSubscriptions()

        viewModel.subscriptionResult.observe(this, Observer {
            if (it.status == Status.SUCCESS && it.data != null) {
                onDataChanged(it.data)
                Timber.d("DATA ${it.data}")
            }
        })
    }

    private fun setupRecyclerView() {
        subscriptionsAdapter =
            PackageItemListAdapter(subscriptions)

        rv_packages.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_packages.adapter = subscriptionsAdapter
    }

    private fun onDataChanged(subscriptionList: List<Subscription>) {
        subscriptions.clear()
        subscriptions.addAll(subscriptionList)
        subscriptionsAdapter.notifyDataSetChanged()
    }
}