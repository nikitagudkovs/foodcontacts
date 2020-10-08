package com.ng.test.foodcontacts.data.repository

import com.ng.test.foodcontacts.data.local.room.RecipeEntity
import com.ng.test.foodcontacts.di.qualifier.LocalData
import com.ng.test.foodcontacts.di.qualifier.RemoteData
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    @LocalData private val localDataSource: RecipeDataSource,
    @RemoteData private val remoteDataSource: RecipeDataSource
) : RecipeDataSource {

    private var cachedRecipes = listOf<RecipeEntity>()
    private var fetchedRecipe: RecipeEntity? = null

    private var cacheRecipesIsDirty = false

    override fun getAllRecipes(): Flowable<List<RecipeEntity>> {

        if (cachedRecipes.isNotEmpty() && !cacheRecipesIsDirty) {
            return Flowable.just(cachedRecipes)
        }

        val remoteRecipes = getAndSaveRemoteRecipes()

        return if (cacheRecipesIsDirty) remoteRecipes else {
            val localRecipes = getAndCacheLocalRecipes()
            Flowable.concat(localRecipes, remoteRecipes)
        }
    }

    override fun getRecipe(id: Int): Single<RecipeEntity> {
        return localDataSource.getRecipe(id).doOnSuccess { recipe -> fetchedRecipe = recipe }
    }

    override fun saveAllRecipes(recipies: List<RecipeEntity>) {
        localDataSource.saveAllRecipes(recipies)
        remoteDataSource.saveAllRecipes(recipies)
    }

    override fun refreshRecipes() {
        cacheRecipesIsDirty = true
    }

    private fun getAndSaveRemoteRecipes(): Flowable<List<RecipeEntity>> {
        return remoteDataSource.getAllRecipes()
            .doOnNext { recipies ->
                localDataSource.saveAllRecipes(recipies)
                cachedRecipes = recipies
            }.doOnComplete {
                cacheRecipesIsDirty = false
            }
    }

    private fun getAndCacheLocalRecipes(): Flowable<List<RecipeEntity>> {
        return localDataSource.getAllRecipes().doOnNext { recipies -> cachedRecipes = recipies }
    }
}