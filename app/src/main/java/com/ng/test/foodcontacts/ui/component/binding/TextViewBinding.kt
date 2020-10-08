package com.ng.test.foodcontacts.ui.component.binding

import androidx.databinding.BindingAdapter
import android.text.Html
import android.widget.TextView

object TextViewBinding {

    @BindingAdapter("app:textHtml")
    @JvmStatic
    fun TextView.textHtml(text: String?) {
        text?.let { setText(Html.fromHtml(it)) }
    }
}