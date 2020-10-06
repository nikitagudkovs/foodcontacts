package com.kotlin.mvvm.foodcontacts.ui.main.home

import android.os.Bundle
import com.kotlin.mvvm.foodcontacts.R
import com.kotlin.mvvm.foodcontacts.ui.main.base.BaseActivity
import com.kotlin.mvvm.foodcontacts.util.ext.addFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.content_frame, ::HomeFragment)
    }

}