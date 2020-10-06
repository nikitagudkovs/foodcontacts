package com.kotlin.mvvm.foodcontacts.data.local

import com.kotlin.mvvm.foodcontacts.data.local.room.RecipeDao
import com.kotlin.mvvm.foodcontacts.data.local.room.RecipeEntity
import com.kotlin.mvvm.foodcontacts.data.repository.RecipeDataSource
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipesLocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeDataSource {

    override fun getAllRecipes(): Flowable<List<RecipeEntity>> {
        return recipeDao.getAllRecipes().toFlowable()
    }

    override fun getRecipe(id: Int): Single<RecipeEntity> {
        return recipeDao.getRecipeById(id)
    }

    override fun saveAllRecipes(recipes: List<RecipeEntity>) {
        recipes.map { recipeDao.insertRecipe(it) }
    }

    override fun refreshRecipes() {

    }
}