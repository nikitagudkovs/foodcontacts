package com.kotlin.mvvm.foodcontacts.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Recipes")
data class RecipeEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("idMeal")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    @SerializedName("strMeal")
    var name: String = "",

    @ColumnInfo(name = "category")
    @SerializedName("strCategory")
    var category: String = "",

    @ColumnInfo(name = "area")
    @SerializedName("strArea")
    var area: String = "",

    @ColumnInfo(name = "instructions")
    @SerializedName("strInstructions")
    var instructions: String = "",

    @ColumnInfo(name = "img")
    @SerializedName("strMealThumb")
    var img: String = ""
)