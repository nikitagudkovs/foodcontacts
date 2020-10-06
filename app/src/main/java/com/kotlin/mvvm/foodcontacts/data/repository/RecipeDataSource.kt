package com.kotlin.mvvm.foodcontacts.data.repository

import com.kotlin.mvvm.foodcontacts.data.local.room.RecipeEntity
import io.reactivex.Flowable
import io.reactivex.Single

interface RecipeDataSource {

    fun getAllRecipes(): Flowable<List<RecipeEntity>>

    fun getRecipe(id: Int): Single<RecipeEntity>

    fun saveAllRecipes(recipes: List<RecipeEntity>)

    fun refreshRecipes()
}