package com.ng.test.foodcontacts.di.module

import androidx.room.Room
import com.ng.test.foodcontacts.EatHimApp
import com.ng.test.foodcontacts.data.local.room.AppDatabase
import com.ng.test.foodcontacts.data.local.room.RecipeDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: EatHimApp): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "food_friends_db.db").build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(database: AppDatabase): RecipeDao = database.recipeDao()

//    @Singleton
//    @Provides
//    fun provideContactDao(database: AppDatabase): ContactDao = database.contactDao()
}