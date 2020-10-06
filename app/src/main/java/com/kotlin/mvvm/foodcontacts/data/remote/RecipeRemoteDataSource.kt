package com.kotlin.mvvm.foodcontacts.data.remote

import com.kotlin.mvvm.foodcontacts.data.local.room.RecipeEntity
import com.kotlin.mvvm.foodcontacts.data.repository.RecipeDataSource
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRemoteDataSource @Inject constructor(
    private val recipeService: RecipeService
) : RecipeDataSource {

    override fun getAllRecipes(): Flowable<List<RecipeEntity>> = recipeService.getRecipes().map { it.meals }

    override fun getRecipe(id: Int): Single<RecipeEntity>  = recipeService.getRecipe(id)

    override fun saveAllRecipes(recipes: List<RecipeEntity>) {
    }

    override fun refreshRecipes() {
    }
}