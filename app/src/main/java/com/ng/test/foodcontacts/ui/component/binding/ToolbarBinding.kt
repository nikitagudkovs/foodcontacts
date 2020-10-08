package com.ng.test.foodcontacts.ui.component.binding

import androidx.databinding.BindingAdapter
import androidx.appcompat.widget.Toolbar
import android.view.View

object ToolbarBinding {

    @BindingAdapter("app:navigationOnClickListener")
    @JvmStatic
    fun Toolbar.setNavigationOnClickListener(onClickListener: View.OnClickListener) {
        setNavigationOnClickListener(onClickListener)
    }
}