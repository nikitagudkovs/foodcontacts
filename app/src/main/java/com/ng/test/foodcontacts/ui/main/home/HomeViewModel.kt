package com.ng.test.foodcontacts.ui.main.home

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableList
import androidx.lifecycle.viewModelScope
import com.ng.test.foodcontacts.data.local.model.Contact
import com.ng.test.foodcontacts.data.local.room.RecipeEntity
import com.ng.test.foodcontacts.data.repository.RecipeRepository
import com.ng.test.foodcontacts.ui.main.base.BaseViewModel
import com.ng.test.foodcontacts.util.ContactHelper
import com.ng.test.foodcontacts.util.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : BaseViewModel() {

    val isRefreshing = ObservableBoolean(false)
    val items: ObservableList<Any> = ObservableArrayList()
    val onRecipeOpenEvent = SingleLiveEvent<RecipeEntity>()
    val onRefreshRequested: PublishSubject<Boolean> = PublishSubject.create()
    val onCallRequested: PublishSubject<String> = PublishSubject.create()

    private var compositeDisposable = CompositeDisposable()

    override fun start() {
        if (compositeDisposable.isDisposed) compositeDisposable = CompositeDisposable()
        if (items.isEmpty()) getAllRecipes(false)
    }

    override fun stop() {
        items.clear()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }

    fun refreshItems() {
        items.clear()
        onRefreshRequested.onNext(true)
    }

    fun openRecipe(recipe: RecipeEntity) {
        compositeDisposable.add(recipeRepository.getRecipe(recipe.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isRefreshing.set(true) }
            .doAfterTerminate { isRefreshing.set(false) }
            .subscribe({ result ->
                onRecipeOpenEvent.value = result
            }, { error ->
                error.printStackTrace()
            })
        )
    }

    fun openContact(contact: Contact) {
        onCallRequested.onNext(contact.number)
    }

    private fun getAllRecipes(mustRefresh: Boolean) {
        if (mustRefresh) recipeRepository.refreshRecipes()

        compositeDisposable.add(recipeRepository.getAllRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isRefreshing.set(true) }
            .doAfterTerminate { isRefreshing.set(false) }
            .subscribe({ result ->
                items.removeAll { it is RecipeEntity }
                items.addAll(result)
            }, { error ->
                error.printStackTrace()
            })
        )
    }
    fun fetchContacts(context: Context) {
        val contactHelper = ContactHelper(context)
        viewModelScope.launch {
            val contactsListAsync = async { contactHelper.getPhoneContacts() }
            val contactNumbersAsync = async { contactHelper.getContactNumbers() }

            val userContacts = contactsListAsync.await()
            val contactNumbers = contactNumbersAsync.await()

            userContacts.forEach {
                contactNumbers[it.id]?.let { numbers ->
                    it.number = numbers.firstOrNull() ?: ""
                }
            }
            items.addAll(userContacts.filter { it.name.isNotBlank() && !items.contains(it) })
        }
    }

    fun reload() = getAllRecipes(true)

}