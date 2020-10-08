package com.ng.test.foodcontacts.di.module

import android.app.Application
import android.content.Context
import com.ng.test.foodcontacts.EatHimApp
import com.ng.test.foodcontacts.di.qualifier.ApplicationContext
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