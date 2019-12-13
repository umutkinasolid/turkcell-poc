package com.solidict.poc.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.solidict.poc.ui.package_activity.data.ChoosePackageViewModel
import com.solidict.poc.ui.main_activity.data.MainViewModel
import com.solidict.poc.viewmodel.PocViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindBulletinViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChoosePackageViewModel::class)
    abstract fun bindChoosePackageViewModel(viewModel: ChoosePackageViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: PocViewModelFactory): ViewModelProvider.Factory
}