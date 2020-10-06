package com.kotlin.mvvm.foodcontacts.di.module

import com.kotlin.mvvm.foodcontacts.data.local.RecipesLocalDataSource
import com.kotlin.mvvm.foodcontacts.data.remote.RecipeRemoteDataSource
import com.kotlin.mvvm.foodcontacts.data.repository.RecipeDataSource
import com.kotlin.mvvm.foodcontacts.di.qualifier.LocalData
import com.kotlin.mvvm.foodcontacts.di.qualifier.RemoteData
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

//    @Singleton
//    @Binds
//    @LocalData
//    abstract fun bindContactLocalDataSource(contactLocalDataSource: ContactsLocalDataSource): ContactDataSource

    @Singleton
    @Binds
    @LocalData
    abstract fun bindRecipeLocalDataSource(recipeLocalDataSource: RecipesLocalDataSource): RecipeDataSource

    @Singleton
    @Binds
    @RemoteData
    abstract fun bindRecipeRemoteDataSource(recipeRemoteDataSource: RecipeRemoteDataSource): RecipeDataSource
}