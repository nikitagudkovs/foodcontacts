package com.kotlin.mvvm.foodcontacts.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface RecipeDao {

    @Query("SELECT * FROM Recipes ORDER BY name ASC")
    fun getAllRecipes(): Maybe<List<RecipeEntity>>

    @Query("SELECT * FROM Recipes WHERE id = :recipeId")
    fun getRecipeById(recipeId: Int): Single<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeEntity)
}