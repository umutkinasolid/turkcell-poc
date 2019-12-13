package com.solidict.poc.di

import com.solidict.poc.ui.package_activity.ChoosePackageActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChoosePackageActivityModule {

    @ContributesAndroidInjector(modules = [FragmentsBuilderModule::class])
    abstract fun contributeChoosePackageActivity(): ChoosePackageActivity
}