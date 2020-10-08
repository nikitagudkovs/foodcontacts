package com.ng.test.foodcontacts.ui.component.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ng.test.foodcontacts.ui.component.adapter.RecipeAdapter

object RecyclerViewBinding {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setRecipeList(recyclerView: RecyclerView, items: List<Any>) {
        with(recyclerView.adapter as RecipeAdapter?) {
            this?.setData(items)
        }
    }
}