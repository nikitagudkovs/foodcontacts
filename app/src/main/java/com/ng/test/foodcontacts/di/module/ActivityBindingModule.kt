package com.ng.test.foodcontacts.di.module

import com.ng.test.foodcontacts.di.ActivityScoped
import com.ng.test.foodcontacts.ui.main.home.HomeModule
import com.ng.test.foodcontacts.ui.main.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}