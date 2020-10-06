package com.kotlin.mvvm.foodcontacts.data.remote

import com.kotlin.mvvm.foodcontacts.data.local.room.RecipeEntity

data class RecipeResponse(var meals: List<RecipeEntity>)
