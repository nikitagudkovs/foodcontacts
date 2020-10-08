package com.ng.test.foodcontacts.di.module

import com.ng.test.foodcontacts.data.local.RecipesLocalDataSource
import com.ng.test.foodcontacts.data.remote.RecipeRemoteDataSource
import com.ng.test.foodcontacts.data.repository.RecipeDataSource
import com.ng.test.foodcontacts.di.qualifier.LocalData
import com.ng.test.foodcontacts.di.qualifier.RemoteData
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