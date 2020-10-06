package com.kotlin.mvvm.foodcontacts.ui.main.home

import com.kotlin.mvvm.foodcontacts.di.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment
}