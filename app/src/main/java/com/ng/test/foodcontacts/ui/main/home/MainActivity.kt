package com.ng.test.foodcontacts.ui.main.home

import android.os.Bundle
import com.ng.test.foodcontacts.R
import com.ng.test.foodcontacts.ui.main.base.BaseActivity
import com.ng.test.foodcontacts.util.ext.addFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(R.id.content_frame, ::HomeFragment)
    }

}