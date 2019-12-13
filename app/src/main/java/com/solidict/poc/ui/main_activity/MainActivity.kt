package com.solidict.poc.ui.main_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.solidict.poc.R
import com.solidict.poc.base.BaseActivity
import com.solidict.poc.ui.main_activity.adapter.MainMenuListAdapter
import com.solidict.poc.ui.main_activity.data.MainViewModel
import com.solidict.poc.ui.package_activity.ChoosePackageActivity
import com.solidict.poc.ui.widgets.CustomBottomBar
import com.solidict.poc.vo.Status
import com.solidict.poc.vo.response_modals.MenuItem
import com.solidict.poc.vo.response_modals.NavBarItem
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.R.attr.divider
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.solidict.poc.util.SpacesItemDecoration


class MainActivity : BaseActivity(), CustomBottomBar.onTabItemSelectedListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.onFetchMenuItems()
        viewModel.onNavBarItems()
        onObserveData()

        bottom_bar.setTabSelectedListener(this)
    }

    override fun onTabItemSelected(viewId: Int) {
        when (viewId) {
            R.id.bottom_bar_ask_us -> {
            }
            R.id.bottom_bar_credit_tl -> {
                val intent = Intent(this, ChoosePackageActivity::class.java)
                startActivity(intent)
            }
            R.id.bottom_bar_home -> {
            }
            R.id.bottom_bar_notif -> {
            }
            R.id.bottom_bar_search -> {
            }
        }
    }


    private fun onObserveData() {
        viewModel.menuItemsResult.observe(this, Observer {
            if (it.status == Status.SUCCESS && it.data != null) {
                initAdapter(it.data)
            }
        })
        viewModel.navBarItemsResult.observe(this,Observer{
            if (it.status==Status.SUCCESS&&it.data!=null){
                initTabMenu(it.data)
            }
        })

    }

    private fun initAdapter(list: List<MenuItem>) {
        rv_menu.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = MainMenuListAdapter(list)
        rv_menu.adapter = adapter


        rv_menu.addItemDecoration(SpacesItemDecoration(this))
    }


    private fun  initTabMenu(navBarItems:List< NavBarItem>){

        bottom_bar_credit_tl.setButtonTitleAndIcon(navBarItems.get(0).label,navBarItems.get(0).imageUrl)
        bottom_bar_notif.setButtonTitleAndIcon(navBarItems.get(1).label,navBarItems.get(1).imageUrl)
        bottom_bar_home.setButtonTitleAndIcon(navBarItems.get(2).label,navBarItems.get(2).imageUrl)
        bottom_bar_search.setButtonTitleAndIcon(navBarItems.get(3).label,navBarItems.get(3).imageUrl)
        bottom_bar_ask_us.setButtonTitleAndIcon(navBarItems.get(4).label,navBarItems.get(4).imageUrl)





    }

}
