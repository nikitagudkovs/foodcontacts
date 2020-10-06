package com.kotlin.mvvm.foodcontacts.di.module

import com.kotlin.mvvm.foodcontacts.di.ActivityScoped
import com.kotlin.mvvm.foodcontacts.ui.main.home.HomeModule
import com.kotlin.mvvm.foodcontacts.ui.main.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}