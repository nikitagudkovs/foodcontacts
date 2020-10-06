package com.kotlin.mvvm.foodcontacts.di.module

import android.app.Application
import android.content.Context
import com.kotlin.mvvm.foodcontacts.EatHimApp
import com.kotlin.mvvm.foodcontacts.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    @ApplicationContext
    abstract fun bindApplicationContext(application: EatHimApp): Context

    @Binds
    abstract fun bindApplication(application: EatHimApp): Application
}