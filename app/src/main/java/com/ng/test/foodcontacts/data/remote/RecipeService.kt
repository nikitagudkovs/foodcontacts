package com.ng.test.foodcontacts.data.remote

import com.ng.test.foodcontacts.data.local.room.RecipeEntity
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("search.php?f=b")
    fun getRecipes(): Flowable<RecipeResponse>

    @GET("lookup.php")
    fun getRecipe(@Query("i") recipeId: Int): Single<RecipeEntity>
}