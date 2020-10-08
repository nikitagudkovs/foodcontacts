package com.ng.test.foodcontacts.ui.component.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ng.test.foodcontacts.R
import com.ng.test.foodcontacts.data.local.model.Contact
import com.ng.test.foodcontacts.data.local.room.RecipeEntity
import com.ng.test.foodcontacts.databinding.ItemContactBinding
import com.ng.test.foodcontacts.databinding.ItemRecipeBinding
import com.ng.test.foodcontacts.ui.main.home.HomeViewModel

class RecipeAdapter(
    private var recipes: List<Any>,
    private val homeViewModel: HomeViewModel?
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = if (viewType == 0) {
            DataBindingUtil.inflate<ItemRecipeBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_recipe, parent, false
            )
        } else {
            DataBindingUtil.inflate<ItemContactBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_contact, parent, false
            )
        }

        return RecipeViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (recipes[position] is RecipeEntity) 0 else 1
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        if (getItemViewType(position) == 0) {
            holder.bindRecipe(recipes[position] as RecipeEntity, object : ItemClickListener {
                override fun onItemSelected(item: Any) {
                    homeViewModel?.openRecipe(item as RecipeEntity)
                }
            })
        } else {
            holder.bindContact(recipes[position] as Contact, object : ItemClickListener {
                override fun onItemSelected(item: Any) {
                    homeViewModel?.openContact(item as Contact)
                }
            })
        }

    class RecipeViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindRecipe(newsEntity: RecipeEntity, newsListener: ItemClickListener) {
            if (binding is ItemRecipeBinding)
                with(binding as ItemRecipeBinding)
                {
                    item = newsEntity
                    listener = newsListener
                    executePendingBindings()
                }
        }

        fun bindContact(Contact: Contact, contactListener: ItemClickListener) {
            if (binding is ItemContactBinding)
                with(binding)
                {
                    item = Contact
                    initial = (item as Contact).name.first().toString()
                    listener = contactListener
                    executePendingBindings()
                }
        }
    }

    fun setData(recipes: List<Any>) {
        this.recipes = recipes.sortedBy { if (it is RecipeEntity) it.name else if (it is Contact) it.name else "" }
        notifyDataSetChanged()
    }
}

interface ItemClickListener {
    fun onItemSelected(item: Any)
}
