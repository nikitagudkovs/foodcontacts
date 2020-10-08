package com.ng.test.foodcontacts.ui.main.home

import com.ng.test.foodcontacts.di.FragmentScoped
import com.ng.test.foodcontacts.ui.main.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun bindDetailFragment(): DetailFragment
}