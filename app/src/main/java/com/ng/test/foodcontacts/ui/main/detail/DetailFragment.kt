package com.ng.test.foodcontacts.ui.main.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.ng.test.foodcontacts.data.local.room.RecipeEntity
import com.ng.test.foodcontacts.databinding.FragmentDetailBinding
import com.ng.test.foodcontacts.di.ActivityScoped
import com.ng.test.foodcontacts.ui.main.base.BaseFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ActivityScoped
class DetailFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var detailViewModel: DetailViewModel

    private lateinit var dataBinding: FragmentDetailBinding

    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        dataBinding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            this.viewModel = detailViewModel
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val recipeJson = args?.getString("selected_recipe", "")
        detailViewModel.recipe = recipeJson?.let { Gson().fromJson(it, RecipeEntity::class.java) }

        detailViewModel.showRecipe()
        detailViewModel.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
        detailViewModel.stop()
    }

}