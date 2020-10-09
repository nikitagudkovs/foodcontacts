package com.ng.test.foodcontacts

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.ng.test.foodcontacts.ui.component.adapter.RecipeAdapter
import com.ng.test.foodcontacts.ui.main.home.MainActivity
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class ProductListFragmentTest {

    val LIST_ITEM_IN_TEST = 2

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun stage1_testIsProductListFragmentVisibleOnAppLaunch() {
        onView(withId(R.id.home_view_recycler)).check(matches(isDisplayed()))
    }

    @Test
    fun stage2_testSelectListItemIsDetailFragmentVisible() {
        onView(withId(R.id.home_view_recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecipeAdapter.RecipeViewHolder>(
                    LIST_ITEM_IN_TEST, click()
                )
            )
    }

    @Test
    fun stage3_testBackNavigationToProductListFragment() {
        onView(withId(R.id.home_view_recycler))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecipeAdapter.RecipeViewHolder>(
                    LIST_ITEM_IN_TEST,
                    click()
                )
            )

        // wait for scroll
        Thread.sleep(500)

        //Navigate to previous product list screen
        pressBack()

        // Confirm ProductListFragment in view
        onView(withId(R.id.home_view_recycler)).check(matches(isDisplayed()))
    }
}
