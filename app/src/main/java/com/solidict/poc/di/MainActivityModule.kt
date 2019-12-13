package com.solidict.poc.di

import com.solidict.poc.ui.main_activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentsBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}