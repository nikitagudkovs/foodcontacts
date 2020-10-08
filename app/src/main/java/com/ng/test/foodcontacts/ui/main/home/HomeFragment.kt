package com.ng.test.foodcontacts.ui.main.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.ng.test.foodcontacts.R
import com.ng.test.foodcontacts.databinding.FragmentHomeBinding
import com.ng.test.foodcontacts.di.ActivityScoped
import com.ng.test.foodcontacts.ui.component.adapter.RecipeAdapter
import com.ng.test.foodcontacts.ui.main.base.BaseFragment
import com.ng.test.foodcontacts.ui.main.detail.DetailFragment
import com.ng.test.foodcontacts.util.ext.hasPermission
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@ActivityScoped
class HomeFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var dataBinding: FragmentHomeBinding

    private lateinit var newsAdapter: RecipeAdapter

    private var disposable: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        newsAdapter = RecipeAdapter(ArrayList(0), homeViewModel)

        dataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            this.viewModel = homeViewModel
            this.adapter = newsAdapter
        }

        observeRefreshRequest()

        return dataBinding.root
    }

    private fun observeRefreshRequest() {
        disposable = homeViewModel.onRefreshRequested
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { refresh ->
                if (refresh) reloadData()
            }
            .subscribe()

        disposable = homeViewModel.onCallRequested
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { number ->
                callFriend(number)
            }
            .subscribe()
    }

    private fun reloadData() {
        loadContacts()
        homeViewModel.reload()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.start()
        handleUIEvent()
        loadContacts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable?.dispose()
        homeViewModel.stop()
    }

    private fun handleUIEvent() {
        homeViewModel.onRecipeOpenEvent.observe(viewLifecycleOwner, Observer { recipe ->
            recipe?.let {
                val detailFragment = DetailFragment()
                val args = Bundle()
                args.putString("selected_recipe", Gson().toJson(it))
                detailFragment.arguments = args
                activity?.supportFragmentManager?.beginTransaction()?.replace(
                    R.id.content_frame,
                    detailFragment,
                    "detailFragment"
                )
                    ?.addToBackStack(this.tag)
                    ?.commit();
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    loadContacts()
                else
                    Toast.makeText(
                        context,
                        "Your contacts won't be imported until you grant the permission. Please refresh to allow access to your contacts.",
                        LENGTH_LONG
                    ).show()
                return
            }

            PERMISSIONS_REQUEST_MAKE_CALL -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED))
                    Toast.makeText(context, "You can call your friends now!", LENGTH_LONG).show()
                else
                    Toast.makeText(
                        context,
                        "Please grant CALL Permission to enable calling from the app.",
                        LENGTH_LONG
                    ).show()
                return
            }
        }
    }


    private fun loadContacts() {
        activity?.let { activity: Activity ->

            if (activity.hasPermission(Manifest.permission.READ_CONTACTS)) {
                homeViewModel.fetchContacts(activity)
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    PERMISSIONS_REQUEST_READ_CONTACTS
                )
            }
        }
    }

    private fun callFriend(number: String) {
        if (number.isNotEmpty()) {
            if (activity?.hasPermission(Manifest.permission.CALL_PHONE) == true) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$number"))
                startActivity(intent)
            } else {
                requestPermissions(
                    arrayOf(Manifest.permission.CALL_PHONE),
                    PERMISSIONS_REQUEST_MAKE_CALL
                )
            }
        } else {
            //(getString(R.string.no_number)).makeToast(this, position = Gravity.TOP, yOffset = -100)
        }
    }

    companion object {
        const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
        const val PERMISSIONS_REQUEST_MAKE_CALL = 111
    }

}