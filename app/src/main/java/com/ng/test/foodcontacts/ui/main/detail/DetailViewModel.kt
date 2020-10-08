package com.ng.test.foodcontacts.ui.main.detail

import androidx.databinding.ObservableField
import com.ng.test.foodcontacts.data.local.room.RecipeEntity
import com.ng.test.foodcontacts.ui.main.base.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor() : BaseViewModel() {

    var recipe: RecipeEntity? = null
    val title = ObservableField("")
    val category = ObservableField("")
    val description = ObservableField("")
    val image = ObservableField("")

    override fun start() {}

    override fun stop() {}

    fun showRecipe() {
        recipe?.let {
            title.set(it.name)
            category.set(it.category)
            description.set(it.instructions)
            image.set(it.img)
        }
    }

}