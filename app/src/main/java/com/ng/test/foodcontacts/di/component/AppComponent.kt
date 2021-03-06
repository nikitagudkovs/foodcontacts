package com.ng.test.foodcontacts.di.component

import com.ng.test.foodcontacts.EatHimApp
import com.ng.test.foodcontacts.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, DatabaseModule::class, NetworkModule::class,
        RepositoryModule::class, ViewModelModule::class, ActivityBindingModule::class]
)
interface AppComponent : AndroidInjector<EatHimApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: EatHimApp): Builder

        fun build(): AppComponent
    }
}