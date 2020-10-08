package com.ng.test.foodcontacts.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    //abstract fun contactDao(): ContactDao
}