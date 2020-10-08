package com.ng.test.foodcontacts.ui.component.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

object SwipeRefreshLayoutBinding {

    @BindingAdapter("app:isRefreshing")
    @JvmStatic
    fun SwipeRefreshLayout.isRefreshing(isRefreshing: Boolean) {
        this.isRefreshing = isRefreshing
    }

    @BindingAdapter("app:onRefreshListener")
    @JvmStatic
    fun SwipeRefreshLayout.setOnRefreshListener(func: () -> Unit) {
        setOnRefreshListener { func() }
    }
}